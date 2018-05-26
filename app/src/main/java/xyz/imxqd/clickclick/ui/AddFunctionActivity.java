package xyz.imxqd.clickclick.ui;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.imxqd.clickclick.R;
import xyz.imxqd.clickclick.dao.DefinedFunction;
import xyz.imxqd.clickclick.func.FunctionFactory;
import xyz.imxqd.clickclick.func.IFunction;

public class AddFunctionActivity extends BaseActivity {

    private static final String PATH_ADD = "add";
    private static final String PATH_SAVE = "save";
    private static final String PATH_RUN = "run";

    @BindView(R.id.add_func_name)
    TextInputEditText etName;
    @BindView(R.id.add_func_description)
    TextInputEditText etDescription;
    @BindView(R.id.add_func_code)
    TextInputEditText etCode;

    private boolean isResumed = false;

    public void handleIntent(Intent intent) {
        if (intent.getData() != null) {
            Uri data = intent.getData();
            try {
                if (PATH_ADD.equals(data.getHost())) {
                    String bodyEncode = data.getEncodedPath().substring(1);
                    String bodyDecode = Uri.decode(bodyEncode);
                    Gson gson = new Gson();
                    DefinedFunction function = gson.fromJson(bodyDecode, DefinedFunction.class);
                    etName.setText(function.name);
                    etDescription.setText(function.description);
                    etCode.setText(function.body);
                } else if (PATH_SAVE.equals(data.getHost())) {
                    String bodyEncode = data.getEncodedPath().substring(1);
                    String bodyDecode = Uri.decode(bodyEncode);
                    Gson gson = new Gson();
                    DefinedFunction function = gson.fromJson(bodyDecode, DefinedFunction.class);
                    try {
                        function.save();
                        showToast(getString(R.string.save_successed));
                    }catch (SQLiteConstraintException e) {
                        showToast(getString(R.string.save_failed));
                    }
                    if (!isResumed) {
                        finish();
                    }
                } else if (PATH_RUN.equals(data.getHost())) {
                    String bodyEncode = data.getEncodedPath().substring(1);
                    String bodyDecode = Uri.decode(bodyEncode);
                    Gson gson = new Gson();
                    DefinedFunction function = gson.fromJson(bodyDecode, DefinedFunction.class);
                    try {
                        IFunction func = FunctionFactory.getFunc(function.body);
                        if (func != null && func.exec()) {
                            showToast(getString(R.string.run_successed));
                        } else {
                            showToast(getString(R.string.run_failed));
                        }

                    }catch (SQLiteConstraintException e) {
                        showToast(getString(R.string.run_failed));
                    }
                    if (!isResumed) {
                        finish();
                    }
                }
            } catch (Exception e) {
                showToast(getString(R.string.open_error));
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_func);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            handleIntent(getIntent());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isResumed = true;
    }

    @OnClick(R.id.add_func_cancel)
    public void onCancelClick() {
        setResult(RESULT_CANCELED);
        finish();
    }

    @OnClick(R.id.add_func_add)
    public void onAddClick() {
        if (checkEmpty()) {
            String name = etName.getText().toString();
            String des = etDescription.getText().toString();
            String code = etCode.getText().toString();
            DefinedFunction function = new DefinedFunction();
            function.name = name;
            function.description = des;
            function.body = code;
            function.order = 0;
            try {
                function.save();
                setResult(RESULT_OK);
                finish();
            } catch (Exception e) {
                showToast(getString(R.string.save_failed));
            }
        }
    }

    @OnClick(R.id.add_func_run)
    public void onRunClick() {
        if (TextUtils.isEmpty(etCode.getText())) {
            etCode.setError(getString(R.string.can_not_be_empty));
            return;
        }
        String data = etCode.getText().toString();
        IFunction function = FunctionFactory.getFunc(data);
        if (function != null && function.exec()) {
            showToast(getString(R.string.run_successed));
        } else {
            showToast(getString(R.string.run_failed));
        }
    }

    private boolean checkEmpty() {
        if (TextUtils.isEmpty(etName.getText())) {
            etName.setError(getString(R.string.can_not_be_empty));
            return false;
        }
        if (TextUtils.isEmpty(etDescription.getText())) {
            etDescription.setError(getString(R.string.can_not_be_empty));
            return false;
        }

        if (TextUtils.isEmpty(etCode.getText())) {
            etCode.setError(getString(R.string.can_not_be_empty));
            return false;
        }
        return true;
    }

}
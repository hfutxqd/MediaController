INSERT INTO defined_function (name, body, description) values ('打开百度', 'url:http://www.baidu.com/', '打开百度');
INSERT INTO defined_function (name, body, description) values ('打开QQ', 'url:mqq://', '打开QQ');
INSERT INTO defined_function (name, body, description) values ('打开微信', 'url:weixin://', '打开微信');
INSERT INTO defined_function (name, body, description) values ('打开支付宝', 'url:alipay:// ', '一键打开支付宝');
INSERT INTO defined_function (name, body, description) values ('支付宝付款', 'url:alipays://platformapi/startapp?appId=20000056', '打开支付宝付款界面');
INSERT INTO defined_function (name, body, description) values ('打开相机', 'action:action://android.media.action.STILL_IMAGE_CAMERA', '打开相机');
INSERT INTO defined_function (name, body, description) values ('打开录像', 'action:action://android.media.action.VIDEO_CAMERA', '打开录像');
INSERT INTO defined_function (name, body, description) values ('微信扫一扫', 'action:intent://#Intent;component=com.tencent.mm/.ui.LauncherUI;B.LauncherUI.From.Scaner.Shortcut=true;end', '打开微信扫一扫');
INSERT INTO defined_function (name, body, description) values ('返回键', 'keyevent:4', '返回');
INSERT INTO defined_function (name, body, description) values ('HOME键', 'keyevent:3', '返回到桌面');
INSERT INTO defined_function (name, body, description) values ('多任务键', 'keyevent:187', '多任务切换程序');
INSERT INTO defined_function (name, body, description) values ('下一曲', 'keyevent:87', '下一曲');
INSERT INTO defined_function (name, body, description) values ('上一曲', 'keyevent:88', '上一曲');
INSERT INTO defined_function (name, body, description) values ('亮度增加', 'keyevent:221', '亮度增加');
INSERT INTO defined_function (name, body, description) values ('亮度减少', 'keyevent:220', '亮度减少');
INSERT INTO defined_function (name, body, description) values ('按键A', 'keyevent:29', '按键A');
INSERT INTO defined_function (name, body, description) values ('组合功能', 'group:["0|keyevent:88|1000","0|keyevent:87|0"]', '上一曲下一曲');
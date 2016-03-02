## html代码

	<!doctype html>
	<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	
	    <meta name="apple-mobile-web-app-capable" content="yes">
	    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
	
	    <title>this's a demo</title>
	    <meta id="viewport" name="viewport"
	          content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,minimal-ui">
	</head>
	<body>
	<div align="center">
	    <a id="open" href="localapp://websit.com" class="label">打开Android本地app</a>
	</div>
	</body>
	</html>

## Android代码

	<activity android:name=".MainActivity">
        <intent-filter>
            <action android:name="android.intent.action.MAIN" />

            <category android:name="android.intent.category.LAUNCHER" />
        </intent-filter>
        <intent-filter>
            <action android:name="android.intent.action.VIEW" />
            <category android:name="android.intent.category.DEFAULT" />
            <category android:name="android.intent.category.BROWSABLE" />
            <data android:host="websit.com" android:scheme="localapp" />
        </intent-filter>
    </activity>
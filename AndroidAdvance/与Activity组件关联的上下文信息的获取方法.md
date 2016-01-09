	public void useActivity(View view){
        Log.e("haha", "getParent():获取父组件（强调包含关系）------->" + MainActivity.this.getParent());
        Log.e("haha","getCallingActivity():获取调用方Activity组件（强调调用关系）------->"+ MainActivity.this.getCallingActivity());
        Log.e("haha","getComponentName():获取Activity组件名称------->"+ MainActivity.this.getComponentName());
        Log.e("haha","getPackageName():获取包名------->"+ MainActivity.this.getPackageName());
        Log.e("haha","getPackageCodePath():获取包代码路径------->"+ MainActivity.this.getPackageCodePath());
        Log.e("haha","getPackageResourcePath():获取包资源路径------->"+ MainActivity.this.getPackageResourcePath());
        Log.e("haha","getFilesDir():获取私有文件夹路径------->"+ MainActivity.this.getFilesDir());
        Log.e("haha","getExternalFilesDir(String type):获取外部文件夹路径------->"+ MainActivity.this.getExternalFilesDir("apk"));
    }






09-24 19:33:56.120  22444-22444/com.example.administrator.myapplication E/haha﹕ getParent():获取父组件（强调包含关系）------->null
09-24 19:33:56.120  22444-22444/com.example.administrator.myapplication E/haha﹕ getCallingActivity():获取调用方Activity组件（强调调用关系）------->null
09-24 19:33:56.120  22444-22444/com.example.administrator.myapplication E/haha﹕ getComponentName():获取Activity组件名称------->ComponentInfo{com.example.administrator.myapplication/com.example.administrator.myapplication.MainActivity}
09-24 19:33:56.125  22444-22444/com.example.administrator.myapplication E/haha﹕ getPackageName():获取包名------->com.example.administrator.myapplication
09-24 19:33:56.125  22444-22444/com.example.administrator.myapplication E/haha﹕ getPackageCodePath():获取包代码路径------->/data/app/com.example.administrator.myapplication-1/base.apk
09-24 19:33:56.125  22444-22444/com.example.administrator.myapplication E/haha﹕ getPackageResourcePath():获取包资源路径------->/data/app/com.example.administrator.myapplication-1/base.apk
09-24 19:33:56.125  22444-22444/com.example.administrator.myapplication E/haha﹕ getFilesDir():获取私有文件夹路径------->/data/data/com.example.administrator.myapplication/files
09-24 19:33:56.145  22444-22444/com.example.administrator.myapplication E/haha﹕ getExternalFilesDir(String type):获取外部文件夹路径------->/storage/emulated/0/Android/data/com.example.administrator.myapplication/files/apk

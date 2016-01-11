# 更改keystore

## build.gradle中的配示例

    apply plugin: 'com.android.application'
    
    android {
        compileSdkVersion 23
        buildToolsVersion "23.0.2"
    
        defaultConfig {
            applicationId "com.qtparking.btool_as"
            minSdkVersion 15
            targetSdkVersion 23
            versionCode 1
            versionName "1.0"
        }
        buildTypes {
            release {
                minifyEnabled false
                proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
            }
        }
    
        signingConfigs {
            debug {
                storeFile file("debug.keystore")// keystore存放位置
            }
        }
    }
    
    dependencies {
        compile fileTree(dir: 'libs', include: ['*.jar'])
        testCompile 'junit:junit:4.12'
        compile 'com.android.support:appcompat-v7:23.1.1'
        compile 'com.github.zhaokaiqiang.klog:library:0.0.1'
        compile 'com.readystatesoftware.systembartint:systembartint:1.0.3'
    }



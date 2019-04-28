# /Users/seemac/Desktop/lucky/github/server_demo/HelloLuckyAndroid/gradlew assembleRelease
#!/bin/bash
/Users/seemac/Desktop/lucky/github/server_demo/HelloLuckyAndroid/gradlew assembleRelease -p /Users/seemac/Desktop/lucky/github/server_demo/HelloLuckyAndroid/
apkPath="/Users/seemac/Desktop/lucky/github/server_demo/HelloLuckyAndroid/app/build/outputs/apk"
apkFile=$apkPath"/app-release.apk"
unzip -qo $apkFile -d $apkPath"/temp"
keytool -printcert -file $apkPath"/temp/META-INF/CERT.RSA"
# nautilus ./app/build/outputs/apk/app-release.apk
adb install /Users/seemac/Desktop/lucky/github/server_demo/HelloLuckyAndroid/app/build/outputs/apk/app-release.apk
# shell
Execute command on Android device Linux shell in Kotlin 

# build
Run below commands in Colab:
```
!wget https://dl.google.com/android/repository/commandlinetools-linux-9123335_latest.zip
!mkdir -p sdk
!unzip commandlinetools-linux-9123335_latest.zip -d sdk
!yes | ./sdk/cmdline-tools/bin/sdkmanager --sdk_root=/content/sdk "tools"
!git clone https://github.com/marzban2030/shell
!chmod -c 755 /content/shell/gradlew
!export ANDROID_HOME=/content/sdk && cd /content/shell && ./gradlew assembleDebug
```

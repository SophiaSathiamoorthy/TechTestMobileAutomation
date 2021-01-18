package POJO;

public class ExecutionConfig {
    public static String localOrRemote;
    public static String IosOrAndroid;
    public String deviceName;
    public static String locale;
    public String platformVersion;
    public String app;
    public String port;
    public String device;
    public String appiumServerUrl;
    private String udid;
    private String systemPort;
    private String wdaLocalPort;

    public static String getLocalOrRemote() {
        return ExecutionConfig.localOrRemote;
    }

    public static void setLocalOrRemote(String localOrRemote) {
        ExecutionConfig.localOrRemote = localOrRemote;
    }

    public static String getIosOrAndroid() {
        return ExecutionConfig.IosOrAndroid;
    }

    public static void setIosOrAndroid(String iosOrAndroid) {
        ExecutionConfig.IosOrAndroid = iosOrAndroid;
    }

    public static String getLocale() {
        return ExecutionConfig.locale;
    }

    public static void setLocale(String locale) {
        ExecutionConfig.locale = locale;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    public void setPlatformVersion(String platformVersion) {
        this.platformVersion = platformVersion;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getAppiumServerUrl() {
        return appiumServerUrl;
    }

    public void setAppiumServerUrl(String appiumServerUrl) {
        this.appiumServerUrl = appiumServerUrl;
    }

    public String getUdid() {
        return udid;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }

    public String getSystemPort() {
        return systemPort;
    }

    public void setSystemPort(String systemPort) {
        this.systemPort = systemPort;
    }

    public String getWdaLocalPort() {
        return wdaLocalPort;
    }

    public void setWdaLocalPort(String wdaLocalPort) {
        this.wdaLocalPort = wdaLocalPort;
    }


}

# Jentis

Jentis is a Package to support app Tracking to Jentis.

## Integration settings

In order to integrate with Jentis SDK you will have to add the following line in the App build.gradle dependencies section. `mavenCentral()` is also required in settings.gradle repositories section.

```java

implementation 'com.jentis:jentis-tracking-sdk:1.0.1'

repositories {
        mavenCentral()
}

```

## Basic usage

First setup the `JentisTrackService` by passing the application context and a `JentisTrackConfig` object to the `initTracking` method. This should pe added either in the Application `onCreate` or in the first Activity.

```java

JentisTrackConfig config = new JentisTrackConfig("https://kndmjh.demoApp.jtm-demo.com/", "demoApp", "live");
JentisTrackService.getInstance().initTracking(getApplicationContext(), config);

```

Afterwards, let the user setup the vendors he allows tracking to. Use the `setConsents` method to pass a Map of String, Bool key pair values of vendors to the SDK.
The setConsents method returns whether it was successful or not.

```java
HashMap<String, Boolean> consents = new HashMap<String, Boolean>();
 consents.put("googleanalytics", true);
 consents.put("easymarketing", false);
 
JentisTrackService.getInstance().setConsents(consents, new ResultHandler<Boolean>() {
        @Override
        public void onSuccess(Boolean success) {

        }

        @Override
        public void onFailure(JentisException e) {

        }
});
```

From now on you can track data using the `push` method. Pass key-value pairs to the method to add information. At least a track key is needed on each push request, use ["track":"submit"] to submit the tracking to the Jenkis server. The other keys can be used to track custom properties. The following values are tracked in the sdk by default.

### Default Trackings

| Key  | Description | Example Value(s) |
| ------------- | ------------- | ------------- |
| app_device_brand  | The manufacturer of the device | Samsung |
| app_device_model  | The device model | GT-S5830L |
| app_device_os | The device operating System | android |
| app_device_os_version | The device os Version + SDK_INT | 5.1.1 22 |
| app_device_language | The device language | English |
| app_device_region | The device region specified in the device settings (not the current location) | US |
| app_device_width | The screen width of the device | 390 |
| app_device_height | The screen height of the device | 844 |
| app_application_name | The name of the application | ExampleApp |
| app_application_version | The app version | 1.0, 2.1 |
| app_application_build_number | The app build number | 1, 2, 3 |

### Example usage

Example usage when the user navigates to a new screen:

```java
 HashMap<String, Object> properties = new HashMap<>();
 properties.put("customProp1", "customVal1");
 properties.put("customProp2", "customVal2");
 properties.put("customProp3", "customVal3");

 ArrayList<String> customArray = new ArrayList<String>();
 customArray.add("jentis");
 customArray.add("sdk");

 HashMap<String, Object> dataView = new HashMap<>();
 dataView.put("track", event.getText().toString());
 dataView.put("pagetitle", pageView.getText().toString());
 dataView.put("virtualPagePath", virtualPagePath.getText().toString());
 dataView.put("customObject", properties);
 dataView.put("customArray", customArray);

 JentisTrackService.getInstance().push(dataView);

HashMap<String, String> dataPush = new HashMap<>();
dataPush.put("track", "submit");
 
JentisTrackService.getInstance().push(dataView);
```

## Debugging

Use the `debugTracking` method to enable/disable debugging.

### Enable Debugging

```java
JentisTrackService.getInstance().debugTracking(true, "62a9e9c727255", "2");
```

### Disable Debugging

```java
JentisTrackService.getInstance().debugTracking(false);
```

## Helper Functions

This methods can be used to retrieve specific data from the SDK.

### getConsentId

Returns the consentID which was created when the user first set his consents.

```java
String consentId = JentisTrackService.getInstance().getCurrentConsentId();
```

### getCurrentConsents

Returns the current consent settings.

```java
Map<String, Boolean> consents = JentisTrackService.getInstance().getCurrentConsents();
```

### getLastConsentUpdate

Returns a Date object of the last time the user updated the consents.

```java
Date lastConsentUpdate = JentisTrackService.getInstance().getLastConsentUpdate();
```

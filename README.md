# WebUI Dex Plugin

A template repository to show off this feature

## Building

```shell
gradlew build-dex
```

## Setup the Plugins

Create a file named `plugins.json` in `/data/adb/modules/<MODID>/webroot` and place the following contents inside

```json
[
  "com.dergoogler.webui.customInterface.WebUIPluginKt",
  "com.dergoogler.webui.dialog.DialogPluginKt"
]
```

## Usage in WebUI

Building a custom dialog from JavaScript with callback

```js
const builder = window.dialog;

window.dialog.positive = () => {
  console.log("Pressed the dialog button!");
};

builder.setTitle("Test");
builder.setMessage("This is a custom dialog");
builder.setPositiveButton("Log me!", "positive");
builder.show();
```

Load a new URL

```js
customInterface.loadUrl("https://google.com");

// show a toast
customInterface.showToast("Hello from a Plugin!");
```

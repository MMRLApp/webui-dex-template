# WebUI Dex Plugin

This repository serves as a template to demonstrate the integration of WebUI plugins, providing a framework to enhance the WebUI experience with custom functionality.

## Building the Plugin

To build the plugin, simply run the following command:

```shell
gradlew build-dex
```

This will compile the necessary code and generate the required `.dex` files for use within your WebUI project.

## Setting Up the Plugins

To get started with the plugin integration, create a file named `plugins.json` within the `/data/adb/modules/<MODID>/webroot` directory. This file will specify the plugins to be loaded. The `.dex`, `.jar`, or `.apk` files associated with these plugins should be placed in the `/data/adb/modules/<MODID>/webroot/plugins` directory for them to be properly loaded and utilized by the WebUI.

Here's an example of what your `plugins.json` file should look like:

```json
[
  "com.dergoogler.mmrl.webui.customInterface.WebUIPluginKt",
  "com.dergoogler.mmrl.webui.dialog.DialogPluginKt"
]
```

Make sure to replace `<MODID>` with your module's actual identifier.

## Using the Plugins in WebUI

Once the plugin setup is complete, you can start utilizing the custom functionalities within your WebUI interface.

### Example: Building a Custom Dialog with a Callback

You can create a custom dialog in JavaScript that reacts to user input by using the provided `dialog` plugin. Here's how to set up a basic dialog with a positive button that triggers a callback when clicked:

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

In this example, when the user clicks on the positive button labeled "Log me!", the callback function logs a message to the console.

### Example: Loading a New URL

You can also load URLs directly into the WebUI with the `customInterface` plugin. Here's a simple example of loading an external URL:

```js
customInterface.loadUrl("https://google.com");
```

### Example: Showing a Toast Notification

To provide feedback to the user, you can display a toast notification using the `customInterface` plugin:

```js
customInterface.showToast("Hello from a Plugin!");
```

This will show a simple toast message on the screen.

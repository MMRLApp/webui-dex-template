# WebUI X Dex Plugin

This repository serves as a template to demonstrate the integration of WebUI plugins, providing a framework to enhance the WebUI experience with custom functionality.

## Building the Plugin

To build the plugin, simply run the following command:

```shell
gradlew build-dex
```

This will compile the necessary code and generate the required `.dex` files for use within your WebUI project.

## Setting Up the Plugins

To get started with the plugin integration, create a file named `config.json` within the `/data/adb/modules/<MODID>/webroot` directory. This file will specify the plugins to be loaded. The `.dex` file associated with these plugins should be placed in the `/data/adb/modules/<MODID>/webroot` directory for them to be properly loaded and utilized by the WebUI X.

Here's an example of what your `config.json` file should look like:

```json
{
  "dexFiles": [
    {
      "path": "plugins/webui.dex",
      "className": "dev.mmrl.webui.TestClass"
    }
  ]
}
```

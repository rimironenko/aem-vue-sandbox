# AEM 6.5 + Vue project

This is an AEM 6.5 project generated from AEM Project Archetype with Vue, Vuex and a few AEM + Vue components.

## Vue inclusion

Vue and Vuex included into build by Webpack and added into source, see ui.apps/webpack.config.js.

Generated source file added to gitignore. Approach is same as for angular/react AEM SPA application samples from Adobe.

## Vue components and dependencies

AEM + Vue components stored in ui.apps/src/main/content/jcr_root/apps/aem-vue-sandbox/components/vue

Vue + Vuex app is initialized in ui.apps/src/main/content/jcr_root/apps/aem-vue-sandbox/clientlibs/clientlib-vue/js

## How to build

To build all the modules run in the project root directory the following command with Maven 3:

    mvn clean install

To build all the modules and deploy the `all` package to a local instance of AEM, run in the project root directory the following command:

    mvn clean install -PautoInstallSinglePackage

Or to deploy it to a publish instance, run

    mvn clean install -PautoInstallSinglePackagePublish

Or alternatively

    mvn clean install -PautoInstallSinglePackage -Daem.port=4503

Or to deploy only the bundle to the author, run

    mvn clean install -PautoInstallBundle

Or to deploy only a single content package, run in the sub-module directory (i.e `ui.apps`)

    mvn clean install -PautoInstallPackage

## Links

* [Maven archetype](https://github.com/adobe/aem-project-archetype)

const path = require('path');

module.exports = {
    mode: 'development',
    entry: {
        main: ['./src/main/content/jcr_root/apps/aem-vue-sandbox/clientlibs/clientlib-vue/js/app.js'] // initial script, entry point, starts out app
    },
    output: {
        filename: '[name].js',
        path: path.resolve(__dirname, './src/main/content/jcr_root/apps/aem-vue-sandbox/clientlibs/clientlib-vue/js') // destination path
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: /(node_modules)/,
                use: {
                    loader: 'babel-loader'
                }
            }
        ]
    },
    resolve: {
        alias: {
            'vue': 'vue/dist/vue.js' // to use vue with compiler
        }
    },
    devtool: 'inline-source-map'

};
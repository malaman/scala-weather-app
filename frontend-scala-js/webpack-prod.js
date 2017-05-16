const Common = require('./webpack-common.js');
const Merge = require('webpack-merge');
const Webpack = require('webpack');
const ExtractTextPlugin = require("extract-text-webpack-plugin");
const CompressionPlugin = require("compression-webpack-plugin");

const ctx = {
    sjs_mode: 'opt',
    mode: 'prod',
    assetDir: '/a/',
    assetFile: '[hash].[ext]',
    output_filename: '[chunkhash].js',

    htmlOptions: {
        minify: {
            removeComments: true,
            collapseWhitespace: true,
        },
    },
};

module.exports = Merge(Common.config(ctx), {

    module: {
        rules: [{
            test: /\.css$/,
            use: ExtractTextPlugin.extract({
                fallback: 'style-loader',
                use: 'css-loader',
            }),
        }]
    },

    plugins: [

        // See webpack's bin/convert-argv.js for what -p expands to
        new Webpack.LoaderOptionsPlugin({
            minimize: true,
        }),
        new Webpack.DefinePlugin({
            'process.env.NODE_ENV': JSON.stringify('production')
        }),
        new Webpack.optimize.UglifyJsPlugin({}),

        // Externalise CSS
        new ExtractTextPlugin({
            filename: 'a/[contenthash].css',
            allChunks: true,
        }),

        // Don't emit anything when errors exist
        new Webpack.NoEmitOnErrorsPlugin(),

        new CompressionPlugin({
            asset: "[path].gz[query]",
            algorithm: "gzip",
            test: /\.(js|css|svg|ttf)$/,
            // threshold: 4096,
            // minRatio: 0.8,
        })
    ]

});

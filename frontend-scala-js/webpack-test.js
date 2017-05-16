const Webpack = require('webpack');
const Path = require('path');

const ctx = {
    assetDir: '/test/assets/',
    assetFile: '[name].[ext]',
};

module.exports = {

    bail: true,

    entry: {
        main: [
            './test-loader.js',
        ],
    },

    output: {
        path: Path.resolve(__dirname, 'target/scala-2.12'),
        filename: 'demo-test-fastopt-bundle.js',
    },

    resolve: {
        alias: {
            'experiment-webpack': Path.resolve(__dirname, 'local_module'),
        },
    },

    plugins: [
      new Webpack.DefinePlugin({
          'process.env.NODE_ENV': JSON.stringify('production')
      }),
    ],

    module: {
        rules: [
            // https://medium.com/@victorleungtw/how-to-use-webpack-with-react-and-bootstrap-b94d33765970#.xrvg55omh
            // url-loader?limit=n means encode the file inline with base64 if the filesize < n, else make it a
            // separate url/link/request.
            {
                test: /\.(woff|woff2)(\?v=\d+\.\d+\.\d+)?$/,
                loader: 'url-loader?limit=10000&mimetype=application/font-woff&name=' + ctx.assetDir + ctx.assetFile,
            }, {
                test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/,
                loader: 'url-loader?limit=10000&mimetype=application/octet-stream&name=' + ctx.assetDir + ctx.assetFile,
            }, {
                test: /\.(?:jpe?g|eot(\?v=\d+\.\d+\.\d+)?)$/,
                loader: 'file-loader?name=' + ctx.assetDir + ctx.assetFile,
            }, {
                test: /\.svg(\?v=\d+\.\d+\.\d+)?$/,
                loader: 'url-loader?limit=10000&mimetype=image/svg+xml&name=' + ctx.assetDir + ctx.assetFile,
            },
        ],
    },
};

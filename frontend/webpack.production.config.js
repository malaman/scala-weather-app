const webpack = require('webpack');
const path = require('path');
const uglifyJsPlugin = webpack.optimize.UglifyJsPlugin;
const CopyWebpackPlugin = require('copy-webpack-plugin');
const HtmlPlugin = require('html-webpack-plugin');

module.exports = {
  entry: [
    path.resolve(__dirname, 'target/scala-2.12/weather-app-opt.js')
  ],
  output: {
    path: path.resolve(__dirname, 'build/assets'),
    filename: 'bundle.[chunkhash].js',
    publicPath: '/assets/'
  },
  module: {
    loaders: [
      {
        test: /\.scss$/,
        use: [
            'style-loader',
            'css-loader',
            'resolve-url-loader',
            'sass-loader?sourceMap'
          ]
      },
      {
        test: /\.css$/,
        use: [
          {
            loader: "style-loader"
          },
          {
            loader: "css-loader",
            options: {
              includePaths: [
                path.resolve('./node_modules/normalize.css/normalize.css'),
                path.resolve('./node_modules/react-select/dist/react-select.css'),
                path.resolve('./node_modules/weather-icons/css/weather-icons.min.css')
              ]
            }
          }
        ]
      },
      {
        test   : /\.(ttf|eot|svg|woff(2)?|png)(\?[a-z0-9=&.]+)?$/,
        use : [
          {
            loader: 'file-loader',
            options: {
              includePaths: [
                path.resolve('./node_modules/weather-icons/font')
              ]
            }
          }
        ]
      }

    ]
  },
  resolve: {
    extensions: ['.js', '.jsx', '.css', '.scss', '.png']
  },
  externals: {
    API_HOST: process.env.API_HOST
  },
  plugins: [
    new uglifyJsPlugin({
      compress: false
    }),
    new HtmlPlugin(Object.assign({
      template: 'assets/index.html',
      filename: '../index.html'
    })),
    new webpack.DefinePlugin({
      'process.env': {
        NODE_ENV: JSON.stringify('production')
      }
    })
  ]
};

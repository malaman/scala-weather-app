const Common = require('./webpack-common.js');
const Merge = require('webpack-merge');
const Path = require('path');
const express = require('express')


const ctx = {
    sjs_mode: 'fastopt',
    mode: 'dev',
    assetDir: '/assets/',
    assetFile: '[name].[ext]',
    output_filename: '[name].js',
};

module.exports = Merge(Common.config(ctx), {

    module: {
        rules: [{
            test: /\.css$/,
            loader: 'style-loader?name=' + ctx.assetDir + ctx.assetFile + '!css-loader',
        }]
    },
    devServer: {
      historyApiFallback: true,
      hot: true,
      inline: true,
      contentBase: './local_module',
      port: 8080,
      setup (app) {
          app.use('/dev/assets/',  express.static(Path.join(__dirname, 'dist', 'dev', 'assets')));
      }
    }
});

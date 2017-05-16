# Setup

1. [Configure Node.JS](https://gist.github.com/japgolly/775314a0cb24e33653b059b8f8540250)
2. `yarn install`

# Building
1. `sbt fastOptJS fullOptJS`
2. `./build`

# Running
1. `./serve.sh`
2. Open in browser:
  * http://localhost:4000/dev/
  * http://localhost:4000/prod/


# Features

- [x] Use scalajs-react without `@JSImport`s
- [x] Use some JS lib with `@JSImport`
- [x] Use assets from some JS lib (CSS + images)
- [x] Use local assets
- [ ] Source maps - pending https://github.com/webpack/webpack/issues/4518
- [x] Separate dev/prod processes
- [x] Output multiple JS files
- [x] `require` bundle assets from SJS
- [x] Hash filenames
  - [x] Images & fonts
  - [x] CSS links
- [x] HTML integration
  - [x] links
  - [x] minification
- [x] gzip
- [x] SJS test deps
- [ ] Externals
  - [ ] Local module
  - [ ] Local script
  - [x] Use CDN - pending https://github.com/jantimon/html-webpack-plugin/issues/613
- [scalajs-bundler](https://github.com/japgolly/misc/tree/scalajs-bundler)


# History
* `yarn add --dev webpack`
* `yarn add --dev expose-loader`
* `yarn add react react-dom`
* `yarn add google-map-react`
* `yarn add --dev css-loader style-loader file-loader url-loader`
* `yarn add bootstrap@3`
* `yarn add --dev extract-text-webpack-plugin`
* `yarn add --dev webpack-merge`
* `yarn add --dev compression-webpack-plugin`
* `yarn add --dev html-webpack-plugin`

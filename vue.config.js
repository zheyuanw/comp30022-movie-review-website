const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    proxy: {
      '/api': {
        target: 'https://moivehub-itproject-team004.herokuapp.com',
        disableHostCheck: true,
        changeOrigin: true,
        // pathRewrite: {
        //   '^api': ''
        // }
      }
    }
  }
})

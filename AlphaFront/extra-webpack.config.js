const path = require('path');

module.exports = {
    resolve: {
        fallback: {
            "http": require.resolve("stream-http"),
            "https": require.resolve("https-browserify"),
            "crypto": require.resolve("crypto-browserify"),
            "fs": false,
            "path": require.resolve("path-browserify"),
            "stream": require.resolve("stream-browserify"),
            "url": require.resolve("url/"),
            "querystring": require.resolve("querystring-es3")
        }
    }
};

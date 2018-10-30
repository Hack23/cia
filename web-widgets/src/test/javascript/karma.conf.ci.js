var baseConfig = require('./karma.conf.js');

module.exports = function (config) {
    // Load base config
    baseConfig(config);

    // Override base config
    config.set({
        singleRun: true,
        colors:    false,
        autoWatch: false,
        reporters: ['progress', 'junit', 'coverage'],
        preprocessors:    {
            'src/main/resources/**/*.js':   ['coverage']
        },
        browsers:  ['PhantomJS'],
        junitReporter: {
            outputFile: '../target/reports/junit/TESTS-xunit.xml'
        },
        coverageReporter: {
            type:   'lcov',
            dir:    'target/reports',
            subdir: 'coverage'
        }
    });
};

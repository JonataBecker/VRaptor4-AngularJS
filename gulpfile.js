var gulp = require('gulp'),
        concat = require('gulp-concat'),
        uglify = require('gulp-uglify'),
        sass = require('gulp-sass'),
        connect = require('gulp-connect'),
        shell = require('gulp-shell');

gulp.task('default', function () {
    gulp.start('watch');
});

gulp.task('scripts', function () {
    gulp.src([
        'src/main/webapp/js/**/*.js',
        '!**/app.js'
    ])
            .pipe(concat('src/main/webapp/js/app.js'))
            .pipe(gulp.dest('.'))
            .pipe(connect.reload());
});
gulp.task('css', function () {
    gulp.src([
        'src/main/webapp/css/*.css',
        '!**/app.css'
    ])
            .pipe(concat('src/main/webapp/css/app.css'))
            .pipe(gulp.dest('.'))
            .pipe(connect.reload());
});
gulp.task('html', function () {
    gulp.src([
        'src/main/webapp/**/*.html'
    ])
            .pipe(connect.reload());
});

gulp.task('watch', function () {
    gulp.watch('src/main/webapp/js/**/*.js', ['scripts']);
    gulp.watch('src/main/webapp/css/*.css', ['css']);
    gulp.watch('src/main/webapp/**/*.html', ['html']);
});

gulp.task('serve', function () {
    connect.server({
        root: 'src/main/webapp',
        port: 8000,
        livereload: true
    });
    gulp.start('watch');
    gulp.src('src/main/webapp/index.html')
            .pipe(shell(['start http://localhost:8000']));
});
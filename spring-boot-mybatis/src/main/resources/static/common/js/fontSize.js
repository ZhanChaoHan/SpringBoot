/**
 * Created by Administrator on 2018/3/21.
 */
var scale = 750 / 1334;

//设置字体
function setFontSize(h) {
    var w = h * scale || window.innerWidth;
    document.documentElement.style.fontSize = 100 * w / 750 + 'px';
}
setFontSize();
window.onresize = setFontSize;
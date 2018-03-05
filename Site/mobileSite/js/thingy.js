var slides  =[];
var captions  =[];
var index = [];
slides[0] = document.getElementsByClassName('Slide-0');
captions[0] = document.getElementsByClassName('figure-caption-0');
index[0] = 0;

slides[1] = document.getElementsByClassName('Slide-1');
captions[1] = document.getElementsByClassName('figure-caption-1');
index[1] = 0;

slides[2] = document.getElementsByClassName('Slide-2');
captions[2] = document.getElementsByClassName('figure-caption-2');
index[2] = 0;

slides[3] = document.getElementsByClassName('Slide-3');
captions[3] = document.getElementsByClassName('figure-caption-3');
index[3] = 0;

var btn_left  = document.getElementsByClassName('Slider-btn-left');
var btn_right  = document.getElementsByClassName('Slider-btn-right');
for (var i = 0; i < btn_left.length; i++) {
  btn_left[i].onclick = function(e){
    Slide_Left(e);
  }
}
for (var i = 0; i < btn_left.length; i++) {
  btn_right[i].onclick = function(e){
    Slide_Right(e);
  }
}

function Slide_Left(e) {
  set = e.target.name;
  // alert(btn_left[set].name);
  tog(slides[set][index[set]]);
  tog(captions[set][index[set]]);
  if(index[set] == 0){
    index[set] = 2;
  }else {
    index[set]--;
  }
  tog(slides[set][index[set]]);
  tog(captions[set][index[set]]);
}
function Slide_Right(e) {
  set = e.target.name;
  // alert(btn_right[set].name);
  tog(slides[set][index[set]]);
  tog(captions[set][index[set]]);
  if(index[set] == 2){
    index[set] = 0;
  }else {
    index[set]++;
  }
  tog(slides[set][index[set]]);
  tog(captions[set][index[set]]);
}
function tog(img){
  img.classList.toggle('hidden');
}

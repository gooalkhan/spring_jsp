/*
	변수 선언 구역
*/

// 월드컵의 이름을 가져올 변수
var DSubject = document.getElementById('dataSubject').innerHTML;

// iN의 모든 값을 다 가져올 imageNameAll
var imageNameAll = document.querySelectorAll('.iN');

// oIN의 모든 값을 다 가져올 originImageNameAll
var originImageNameAll = document.querySelectorAll('.oIN');

// imageName을 배열에 넣어줄 imageName
var imageName = [];

// originImageName을 배열에 넣어줄 originImageName
var originImageName = [];

// 승리한 imageName을 임시적으로 넣어줌
var winImageName = [];

// 승리한 originImageName을 임시적으로 넣어줌
var winOriginImageName = [];

// 임시 imageName(왼쪽)
var tempiNL;

// 임시 originImageName(왼쪽)
var tempoINL;

// 임시 imageName(왼쪽)
var tempiNR;

// 임시 originImageName(오른쪽)
var tempoINR;

// 매치업 수를 저장할 변수
var matchupNum = 0;

// 총 매치업 수를 저장할 변수
var matchupNumAll;

// 현재 몇 강인지 저장할 변수
var remainNum;

// 몇 강인지 표시할 변수
var remainNumGang;

// 승리한 이미지 저장
var winImg;

// 승리한 이미지 이름 저장
var winImgName;

/*
	함수 선언 구역
*/

// 게임 시작 함수
function startGame() {
	// 반복문을 써서 imageNameAll의 수 만큼, imageName에 값을 넣어줌
	for (var i = 0; i < imageNameAll.length; i++) {
		imageName.push(imageNameAll[i].innerHTML);
	}
	
	// 반복문을 써서 originImageNameAll의 수 만큼, originImageName에 값을 넣어줌
	for (var i = 0; i < originImageNameAll.length; i++){
		originImageName.push(originImageNameAll[i].innerHTML);
	}
	//잘 나오는지 안나오는지 확인하는 코드
	/*
	alert(originImageNameAll[0]);
	alert(imageNameAll[0]);
	alert(imageName[0]);
	alert(originImageName[0]);
	*/
	matchupNum = 0;
	matchupNumAll = imageName.length / 2;
	// 소수점 이라면
	if(matchupNumAll%2 != 0 && matchupNumAll%2 != 1){
		matchupNumAll =  matchupNumAll + 0.5;
	}
	
	// 몇 강인지 표시해주는 과정 처리
	if(matchupNumAll <= 0.5 && matchupNumAll > 0){
		remainNumGang = "결승전";
	} else if(matchupNumAll <= 1 && matchupNumAll > 0.5){
		remainNumGang = "준결승전";
	} else if(matchupNumAll <= 2 && matchupNumAll > 1){
		remainNum = 4;
		remainNumGang = remainNum + "강";
	} else if(matchupNumAll <= 4 && matchupNumAll > 2){
		remainNum = 8;
		remainNumGang = remainNum + "강";
	} else if(matchupNumAll <= 8 && matchupNumAll > 4){
		remainNum = 16;
		remainNumGang = remainNum + "강";
	} else if(matchupNumAll <= 16 && matchupNumAll > 8){
		remainNum = 32;
		remainNumGang = remainNum + "강";
	} else if(matchupNumAll <= 32 && matchupNumAll > 16){
		remainNum = 64;
		remainNumGang = remainNum + "강";
	} else if(matchupNumAll <= 64 && matchupNumAll > 32){
		remainNum = 128;
		remainNumGang = remainNum + "강";
	} else if(matchupNumAll <= 126 && matchupNumAll > 65){
		remainNum = 256;
		remainNumGang = remainNum + "강";
	} else if(matchupNumAll <= 256 && matchupNumAll > 128){
		remainNum = 512;
		remainNumGang = remainNum + "강";
	} else if(matchupNumAll <= 512 && matchupNumAll > 256){
		remainNum = 1024;
		remainNumGang = remainNum + "강";
	}

    displayNextMatchup();
}

// 게임 진행 함수
function displayNextMatchup() {
	// 부전승 처리
	if (imageName.length == 1){
		alert("자료가 하나 남았습니다. 하나남은 자료는 부전승으로 올라갑니다.")
		randNum();
		document.getElementById("leftImage").src = "/resources/worldcupimages/" + tempiNL;
		document.getElementById("leftImageName").innerText = tempoINL;
		document.getElementById("rightImage").src = "/resources/worldcupimages/" + tempiNL;
		document.getElementById("rightImageName").innerText = tempoINL;
		winImageL();
		count--;
	}
    if (imageName.length == 0) {
        // 현재 라운드의 매치업이 모두 종료된 경우
        if (winImageName.length == 1) {
            // winImageName에 데이터가 1개만 있을 경우에(최종 승자가 결정됬을 경우에) 끝났다는 창 출력
            // TODO: 여기 월드컵이 끝났습니다. 가 아니라 worldCupResult를 만들어서 결과를 볼 수 있도록 해줘야됨
            alert("월드컵이 끝났습니다.");
           	location.href = "/worldCupResult";
           	// 결과 페이지에서 결과가 보여지도록 처리하기 위한 코드
            winImg = winImageName[0];
            winImgName = winOriginImageName[0];
            sessionStorage.setItem('sWCimg', winImg);
            sessionStorage.setItem('sWCimgName', winImgName);
        } else {
            // 그렇지 않다면, 다음 라운드로 이동
            alert("다음 라운드가 실행됩니다.");
            
	        // 매치업 초기화
	        matchupNum = 0;
	        
	        // 총 매치업 나누기 2
	        matchupNumAll = matchupNumAll/2;
	        // 소수점 이라면
			if(matchupNumAll%2 != 0 && matchupNumAll%2 != 1){
				matchupNumAll =  matchupNumAll + 0.5;
			}
	        
	        // 몇 강인지 표시해주는 과정 처리
	        // 남은 매치업이 2개라면,(즉, 준결승전이라면)
	        if(matchupNumAll == 2){
				remainNumGang = "준결승전";
			// 남은 매치업이 1개라면,(즉, 결승전이라면)
			} else if(matchupNumAll == 1){
				remainNumGang = "결승전";
			// 그렇지 않은 경우라면
			} else {
				remainNum = remainNum/2;
				remainNumGang = remainNum + "강";
			}
	        
	        // 이긴 값들을 원래 배열에 넣어줌
            imageName = imageName.concat(winImageName);
            originImageName = originImageName.concat(winOriginImageName);
            
            // 임시 배열 초기화
            winImageName = [];
            winOriginImageName = [];
            
            displayNextMatchup();
        }
    } else {
//		alert("다음 대진이 실행됩니다.");
        // 다음 매치업
        
        // 랜덤 실행
        randNum();
        
//      alert(tempiNL);
		document.getElementById("leftImage").src = "/resources/worldcupimages/" + tempiNL;
		document.getElementById("leftImageName").innerText = tempoINL;
		
//		alert(tempiNR);
		document.getElementById("rightImage").src = "/resources/worldcupimages/" + tempiNR;
		document.getElementById("rightImageName").innerText = tempoINR;
		
		//매치업 수를 증가
		matchupNum ++;
		
		document.getElementById("worldCupSubject").innerHTML = DSubject + " " + remainNumGang + " " + matchupNum + "/" + matchupNumAll;
    }
}

// 왼쪽 이미지를 클릭했을 때, 실행되는 함수
function winImageL() {
	// 이긴 값을 임시 배열에 넣고
	winImageName.push(tempiNL);
	winOriginImageName.push(tempoINL);
	// 썼던 값들은 삭제해줌
	deleteImage(tempiNL, tempoINL, tempiNR, tempoINR);
	displayNextMatchup();
}

// 오른쪽 이미지를 클릭했을 때, 실행되는 함수
function winImageR() {
	// 이긴 값을 임시 배열에 넣고
	winImageName.push(tempiNR);
	winOriginImageName.push(tempoINR);
	// 썼던 값들은 삭제해줌
	deleteImage(tempiNL, tempoINL, tempiNR, tempoINR);
	displayNextMatchup();
}

// 이미 라운드를 진행한 배열 안에 이미지들을 삭제하는 함수
function deleteImage(iNL, oINL, iNR, oINR){
	var iNIndexL = imageName.indexOf(iNL);
	if (iNL !== -1) {
		imageName.splice(iNIndexL, 1);
	}
	var oINIndexL = originImageName.indexOf(oINL);
	if (iNR !== -1) {
		originImageName.splice(oINIndexL, 1);
	}
	var iNIndexR = imageName.indexOf(iNR);
	if (oINL !== -1) {
		imageName.splice(iNIndexR, 1);
	}
	var oINIndexR = originImageName.indexOf(oINR);
	if (oINR !== -1) {
		originImageName.splice(oINIndexR, 1);
	}
}

// 랜덤한 자리의 배열의 값을 뽑게해주는 함수
function randNum() {
	// 0 ~ 배열의 길이-1 만큼의 무작위 값 생성 후, 그 자리에 있는 배열 값을 꺼내옴(왼쪽 이미지)
	var randL = Math.floor(Math.random() * originImageName.length);
	tempiNL = imageName[randL];
	tempoINL = originImageName[randL];
	
	// 0 ~ 배열의 길이-1 만큼의 무작위 값 생성 후, 그 자리에 있는 배열 값을 꺼내옴(오른쪽 이미지)
	var randR = Math.floor(Math.random() * originImageName.length);
	// 이때, randL가 randR의 값이 같으면 안됨으로, 다를 때 까지 randR을 돌려줌
	while (randL == randR && imageName.length != 1){
		randR = Math.floor(Math.random() * originImageName.length);
	}
	tempiNR = imageName[randR];
	tempoINR = originImageName[randR];
}

// 마지막에 결과값을 보기 위한 함수
function endGame() {
	var endImg = sessionStorage.getItem('sWCimg');
    var endImgName = sessionStorage.getItem('sWCimgName');
	document.getElementById("winnerImg").src = "/resources/worldcupimages/" + endImg;
	document.getElementById("winnerImgName").innerText = endImgName;
}



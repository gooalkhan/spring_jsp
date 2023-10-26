package com.example.spring_jsp.worldcup;

import java.io.File;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class WorldCupController {
	private final WorldCupService worldCupService;
	
	// 이상형 월드컵 만들기 페이지
	@GetMapping("/worldCupCreate")
	public String worldCupCreate() {
		return "/worldcup/worldCupCreate";
	}
	
	// 이상형 월드컵 만들기 처리
	@PostMapping("/worldCupCreate")
	public ModelAndView worldCupCreatePost(WorldCupDTO worldCupDTO, HttpServletRequest request, MultipartFile[] uploadfile) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
	    if (session.getAttribute("sid") == null) {
	    	request.setAttribute("msg", "로그인 시 작성 가능합니다. 로그인 해주세요.");
	    	request.setAttribute("url", "/memberLogin");
	        mav.setViewName("/alert");
		}else {
			// 여긴 엔터 입력 시, 줄바꿈이 필요 없음
			// 여긴 굳이 알림창을 띄울 필요가 없어서 안 띄움
			this.worldCupService.worldCupInsert(worldCupDTO);
			int idx = worldCupDTO.getIdx();
			worldCupDTO.setWorldcuptbl_idx(idx);
			for(MultipartFile files : uploadfile) {
				// 폴더 생성과 파일명 새로 부여를 위한 현재 시간 알아내기
			    LocalDateTime now = LocalDateTime.now();
			    int year = now.getYear();
			    int month = now.getMonthValue();
			    int day = now.getDayOfMonth();
			    int hour = now.getHour();
			    int minute = now.getMinute();
			    int second = now.getSecond();
			    int millis = now.get(ChronoField.MILLI_OF_SECOND);
			    String imageName = files.getOriginalFilename();
			    worldCupDTO.setOriginImageName(imageName);
			    String absolutePath = new File("./src/main/webapp/resources").getAbsolutePath() + "\\"; // 파일이 저장될 절대 경로
			    String newFileName = "image"+ year + month + day + hour + minute + second + millis; // 새로 부여한 이미지명
			    String fileExtension = '.' + imageName.replaceAll("^.*\\.(.*)$", "$1"); // 정규식 이용하여 확장자만 추출
			    String path = "worldcupimages"; // 저장될 폴더 경로
			    String realPath = absolutePath + path;
			    String realName = newFileName + fileExtension;
			    try {
			    	if(!files.isEmpty()) {
			    		File file = new File(absolutePath + path);
			            if(!file.exists()){
			                file.mkdirs();
			                // mkdir() 만들고자 하는 디렉토리의 상위 디렉토리가 존재하지 않을 경우, 생성 불가
			                // mkdirs() 만들고자 하는 디렉토리의 상위 디렉토리가 존재하지 않을 경우, 상위 디렉토리까지 생성
			            }
			            file = new File(absolutePath + path + "\\" + newFileName + fileExtension);
			            files.transferTo(file);


			            worldCupDTO.setImageName(realName);
			            worldCupDTO.setImagePath(realPath);

			            this.worldCupService.worldCupImageInsert(worldCupDTO);
			            Thread.sleep(10); // 너무 빠르면, 이미지 이름이 같아지면서 덮어 씌워져 버림
			    	}
			    	
			    } catch (Exception e) {
			    	e.printStackTrace();
			    }
			}
			mav.setViewName("redirect:/worldCupList");
		}
	    return mav;
	}
	
	// 이상형 월드컵 리스트 페이지
	@GetMapping("/worldCupList")
	public ModelAndView worldCupList(WorldCupDTO worldCupDTO, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		List<WorldCupDTO> DTO = worldCupService.worldCupSelect(worldCupDTO);
		List<WorldCupDTO> IDTO = worldCupService.worldCupImageSelect(worldCupDTO);
		mav.addObject("data", DTO);
		mav.addObject("image", IDTO);
		mav.setViewName("/worldcup/worldCupList");
		return mav;
	}
	
	// 이상형 월드컵 진행 페이지(임시 작동 안함)
	@GetMapping("/worldCupProc")
	public ModelAndView worldCupProc(WorldCupDTO worldCupDTO, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		WorldCupDTO DTO = worldCupService.worldCupProcSelect(worldCupDTO);
		List<WorldCupDTO> IDTO = worldCupService.worldCupProcImageSelect(worldCupDTO);
		mav.addObject("data", DTO);
		mav.addObject("image", IDTO);
		mav.setViewName("/worldcup/worldCupProc");
		return mav;
	}
}

package com.example.spring_jsp.board;

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
public class BoardController {
	private final BoardService boardService;
	
	//게시글 목록
	@GetMapping("/boardList")
	public ModelAndView boardList() throws Exception{
		ModelAndView mav = new ModelAndView();
		List<BoardDTO> list = boardService.boardListJoin();
		mav.addObject("data", list);
		mav.setViewName("/board/boardList");
		return mav;
	}
	
	//게시글 쓰기 페이지
	@GetMapping("/boardInsert")
	public String boardInsert() {
		return "/board/boardInsert";
	}
	
	//게시글 쓰기 처리
	/*
	ServiceImpl에서 짰던 것 처럼, id가 null이라면(insert가 성공적으로 이루어지지 않았다면)
	boardInsert로 리다이렉트 되고
	null이 아니라 값이 있다면
	boardList로 리다이렉트됨
	*/
	@PostMapping("/boardInsertPost")
	public ModelAndView boardInsertPost(BoardDTO boardDTO, HttpServletRequest request, MultipartFile[] uploadfile) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
	    if (session.getAttribute("sid") == null) {
	    	request.setAttribute("msg", "로그인 시 작성 가능합니다. 로그인 해주세요.");
	    	request.setAttribute("url", "/memberLogin");
	        mav.setViewName("/alert");
		}else {
			// 엔터 입력 시 , 줄 바꿈
			String content = boardDTO.getContent();
			content = content.replace("\r\n", "<br>");
			boardDTO.setContent(content);
			// 여긴 굳이 알림창을 띄울 필요가 없어서 안 띄움
			this.boardService.boardInsert(boardDTO);
			int idx = boardDTO.getIdx();
			boardDTO.setBoardtbl_idx(idx);
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
			    boardDTO.setOriginImageName(imageName);
			    String absolutePath = "C:\\FileIO\\images\\"; // 파일이 저장될 절대 경로
			    String newFileName = "image"+ year + month + day + hour + minute + second + millis; // 새로 부여한 이미지명
			    String fileExtension = '.' + imageName.replaceAll("^.*\\.(.*)$", "$1"); // 정규식 이용하여 확장자만 추출
			    String path = "boardimages"; // 저장될 폴더 경로
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


			            boardDTO.setImageName(realName);
			            boardDTO.setImagePath(realPath);

			            this.boardService.imageUpload(boardDTO);
			            Thread.sleep(10); // 너무 빠르면, 이미지 이름이 같아지면서 덮어 씌워져 버림
			    	}
			    	
			    } catch (Exception e) {
			    	e.printStackTrace();
			    }
			}
		    
			
			mav.setViewName("redirect:/boardList");
		}
		return mav;
	}
	
	//게시글 상세 정보
	/*
	DTO는 글 상세정보를 보기 위함이고, CDTO는 댓글들을 보기 위함
	각각을 view에서 활용해주기 위해 mav.addObject로 key와 value를 담아서 보내줌
	boardDetail로 요청이 올 때 마다 조회수를 올리기 위해 boardService.boardView(idx); 추가
	*/
	@GetMapping("/boardDetail")
	public ModelAndView boardDetail(BoardDTO boardDTO, HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView();
		int idx = boardDTO.getIdx();
		boardService.boardView(idx);
		HttpSession session = request.getSession();
		BoardDTO DTO = boardService.boardDetailJoin(boardDTO);
		List<BoardDTO> CDTO = boardService.commentShow(boardDTO);
		
		mav.addObject("data", DTO);
		mav.addObject("show", CDTO);
		
		if(session.getAttribute("sid") != null) {
			String id = session.getAttribute("sid").toString();
			boardDTO.setId(id);
		}
		BoardDTO LDTO = boardService.likeButton(boardDTO);
		mav.addObject("like", LDTO);
		boardDTO.setBoardtbl_idx(DTO.getIdx());
		List<BoardDTO> IDTO = boardService.imageSelect(boardDTO);
		mav.addObject("image", IDTO);
		mav.setViewName("/board/boardDetail");
		return mav;
	}
	
	//게시글 수정 페이지
	@GetMapping("/boardUpdate")
	public ModelAndView boardUpdate(BoardDTO boardDTO) throws Exception{
		ModelAndView mav = new ModelAndView();
		BoardDTO DTO = this.boardService.boardDetail(boardDTO);
		mav.addObject("data", DTO);
		mav.setViewName("/board/boardUpdate");
		return mav;	
	}
	
	//게시글 수정 처리
	@PostMapping("/boardUpdate")
	public ModelAndView boardUpdatePost(BoardDTO boardDTO, HttpServletRequest request, MultipartFile[] uploadfile) {
		ModelAndView mav = new ModelAndView();
		// 엔터 입력 시 , 줄 바꿈
		String content = boardDTO.getContent();
		content = content.replace("\r\n", "<br>");
		boardDTO.setContent(content);
		
		boolean isUpdateSuccess = this.boardService.boardUpdate(boardDTO);
		
		if(isUpdateSuccess) {
			int idx = boardDTO.getIdx();
			boardDTO.setBoardtbl_idx(idx);
			// 게시글 수정 시, 이미지 폴더에 기존 이미지 삭제
			List<BoardDTO> IDTO = boardService.imageSelect(boardDTO);
			String aPath = "C:\\FileIO\\images\\"; // 파일이 저장될 절대 경로
		    String sPath = "boardimages";
		    String rPath = aPath + sPath;
			for(BoardDTO DTO: IDTO) {
				String filePathStr = rPath + "\\" + DTO.getImageName();
				File file = new File(filePathStr);
				file.delete();
			}
			this.boardService.imageDelete(boardDTO);
			
			// 여긴 굳이 알림창을 띄울 필요가 없어서 안 띄움
			boardDTO.setBoardtbl_idx(idx);
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
			    boardDTO.setOriginImageName(imageName);
			    String absolutePath = "C:\\FileIO\\images\\"; // 파일이 저장될 절대 경로
			    String newFileName = "image"+ year + month + day + hour + minute + second + millis; // 새로 부여한 이미지명
			    String fileExtension = '.' + imageName.replaceAll("^.*\\.(.*)$", "$1"); // 정규식 이용하여 확장자만 추출
			    String path = "boardimages"; // 저장될 폴더 경로
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


			            boardDTO.setImageName(realName);
			            boardDTO.setImagePath(realPath);

			            this.boardService.imageUpload(boardDTO);
			            Thread.sleep(10); // 너무 빠르면, 이미지 이름이 같아지면서 덮어 씌워져 버림
			    	}
			    	
			    } catch (Exception e) {
			    	e.printStackTrace();
			    }
			}
		    
	    	request.setAttribute("msg", "수정이 완료되었습니다.");
	    	request.setAttribute("url", "/boardDetail?idx="+idx);
			mav.setViewName("/alert");
		} else {
	    	request.setAttribute("msg", "올바르지 않은 수정입니다.");
	    	request.setAttribute("url", "/boardList");
	        mav.setViewName("/alert");
		}
		return mav;
	}
	
	//게시글 삭제 처리
	@PostMapping("/boardDelete")
	public ModelAndView boardDeletePost(BoardDTO boardDTO, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		int idx = boardDTO.getIdx();
		boardDTO.setBoardtbl_idx(idx);
		// 게시글 수정 시, 이미지 폴더에 기존 이미지 삭제
		List<BoardDTO> IDTO = boardService.imageSelect(boardDTO);
		String aPath = "C:\\FileIO\\images\\";
	    String sPath = "boardimages";
	    String rPath = aPath + sPath;
		for(BoardDTO DTO: IDTO) {
			String filePathStr = rPath + "\\" + DTO.getImageName();
			File file = new File(filePathStr);
			file.delete();
		}
		
		this.boardService.imageDelete(boardDTO);
		
		boolean isDeleteSuccess = this.boardService.boardDelete(boardDTO);
		//TODO: 댓글이 달려있으면 게시물이 지워지지 않으므로 cascasde로 처리하던 해야함
		if(isDeleteSuccess) {
	    	request.setAttribute("msg", "삭제가 완료되었습니다.");
	    	request.setAttribute("url", "/boardList");
			mav.setViewName("/alert");
		} else {
	    	request.setAttribute("msg", "올바르지 않은 삭제입니다.");
	    	request.setAttribute("url", "/boardDetail?idx="+idx);
	        mav.setViewName("/alert");
		}
		return mav;
	}
	
	//게시글 찾기
	@GetMapping("/boardSearch")
	public ModelAndView boardSearch(String subject) throws Exception {
		ModelAndView mav = new ModelAndView();
		List<BoardDTO> DTO = this.boardService.boardSearch(subject);
		mav.addObject("data", DTO);
		mav.setViewName("/board/boardList");
		return mav;
	}
}

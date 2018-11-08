//package com.biz.board;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.io.PrintWriter;
//import java.text.DateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Locale;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
////error
////import org.codehaus.jackson.map.ObjectMapper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.ModelAndView;
//import com.google.gson.Gson;
//
//@Controller
//public class BoardController {
//	
//	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
//
//	@Autowired
//	private BoardService svc;
//	
//	@RequestMapping(value = "/down/{fname:.+}", method = RequestMethod.GET)
//	public void filedown(
//			HttpServletResponse response,
//			@PathVariable String fname) {  
//		//MIME type of the file
//		String mimeType = "application/octet-stream";
//		System.out.println("MIME type: " + mimeType);
//
//		//modifies HTTP header response
//		File downloadFile = new File("c:/uploads/"+fname);
//		FileInputStream inStream = null;
//		OutputStream outStream = null;
//		try {
//			inStream = new FileInputStream(downloadFile);
//			response.setContentType(mimeType);
//			response.setContentLength((int) downloadFile.length());
//
//			String headerKey = "Content-Disposition";
//			String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
//			response.setHeader(headerKey, headerValue);
//
//			//send http response using output stream
//			outStream = response.getOutputStream();
//			byte[] buffer = new byte[4096];
//			int bytesRead = -1;
//			while ((bytesRead = inStream.read(buffer)) != -1) {
//				outStream.write(buffer, 0, bytesRead);
//			}
//			
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				inStream.close();
//				outStream.close(); 
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		  
//	}
//	
//	
//	@RequestMapping(value = "/write", method = RequestMethod.POST)
//	public String write(BoardVO vo) {  					//uid=kim&upw=111&
//		
////	public String write(@ModelAttribute BoardVO vo) { 	//uid=kim&upw=111&
////	public String write(@RequestBody BoardVO vo) {		//{uid:kim , upw:111}
////	public String write(@RequestParam(value="bseq", required=false, defaultValue="1") int bseq ,
////	public String write(@PathVariable int bseq,			//  /view/64		
////	public String write(HttpServletRequest request) {
//		//MultipartHttpServletRequest mrequest
//		//MultipartFile f = request.getParameter("ufile");
//		//BoardVO  MultipartFile get/set
//		
////		String title = request.getParameter("title");
////		String contents = request.getParameter("contents");
////		String userid = request.getParameter("userid");
////		BoardVO vo = new BoardVO();
////		vo.setTitle(title);
////		vo.setContents(contents);
//		
//		String uploadPath = "C:/uploads";
//		MultipartFile mfile = vo.getUfile();
//		vo.setUfileSize((int)mfile.getSize());
//		vo.setUfilePath(uploadPath);
//		vo.setUfileName(mfile.getOriginalFilename());
//		vo.setUfileSysName(mfile.getOriginalFilename());
//		
//	
//		// c:/uploads/aa.jpg
//		File f = new File(uploadPath + "/" + mfile.getOriginalFilename());
//		try {
//			mfile.transferTo(f); //파일카피
//		} catch (IllegalStateException e1) {
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		vo.setUserid("kim");
//		try {
//			int res = svc.svcInsert(vo);
//		} catch (Exception e) {
//			System.out.println("contorller 잡았어..");
//		}
//		return "redirect:/list";
//	}
//	
//	@RequestMapping(value = "/brlist", method = RequestMethod.GET)
//	public ModelAndView brlist( ) {
//		System.out.println("Controller ......");
//		ArrayList<BoardVO> brlist = svc.svcBRList();
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("LVL_BRLIST", brlist);
//		mav.setViewName("board/brlist");
//		return mav;
//	}
//	
//	@RequestMapping(value = "/list", method = RequestMethod.GET)
//	public ModelAndView list( ) {
//		System.out.println("Controller ......");
//		ArrayList<BoardVO> list = svc.svcList();
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("LVL_LIST", list);
//		mav.setViewName("board/list");
//		return mav;
//	}
//	
////	@RequestMapping(value = "/view/{bseq}/{uid}", method = RequestMethod.GET)
//	@RequestMapping(value = "/view/{bseq}", method = RequestMethod.GET)
//	public ModelAndView view(
//
//			//@RequestParam(value="bseq", required=false, defaultValue="1") int bseq ,
//			//@RequestParam("bseq") int bseq
//			@PathVariable int bseq
//			//,@PathVariable String uid
//	) {		
//		
////	public ModelAndView view(HttpServletRequest request) {
////		String bseqStr = request.getParameter("bseq");
////		int bseq = 1;
////		if(bseqStr != null || !bseqStr.equals("")) {
////			bseq = Integer.parseInt(bseqStr);
////		}
//		System.out.println("Controller ......");
//		
//		BoardVO bvo = svc.svcSelect(bseq); //vo.getBseq()
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("keybvo", bvo);
//		mav.setViewName("board/view");
//		return mav;
//	}
//	
//	
//	@RequestMapping(value = "/update", method = RequestMethod.POST)
//	public String update(HttpServletRequest request) {
//		
//		BoardVO bvo = new BoardVO();
//		String bseqStr = request.getParameter("bseq");
//		int bseq = 1;
//		if(bseqStr != null || !bseqStr.equals("")) {
//			bseq = Integer.parseInt(bseqStr);
//		}
//		String title = request.getParameter("title");
//		String contents = request.getParameter("contents");
//		bvo.setBseq(bseq);
//		bvo.setTitle(title);
//		bvo.setContents(contents);
//		
//		int res = svc.svcUpdate(bvo);
//		if(res <= 0) {
//			return "redirect:/list";
//		} else {
//			return "redirect:/view/"+bseq;
//		}
//	}
//	
//	
//	
//	@RequestMapping(value = "/delete", method = RequestMethod.POST)
//	public String delete(BoardVO bvo) {
//		int res = svc.svcDelete(bvo);
//		return "redirect:/list";
//	}
//	
//	
//	//-----------------------------------------------------------
//	
////	-----------------org.codehaus.jackson.map.ObjectMapper -------------------	
////	@RequestMapping(value = "/reply_list")
////	@ResponseBody
////	public void resplyList(
////			@RequestParam("bseq") int bseq,
////	        HttpServletResponse response) {
////		
////		ArrayList<BoardReplyVO> rlist = svc.svcReplyList(bseq);
////	    try {
////			ObjectMapper mapper = new ObjectMapper();
////	        response.getWriter().print(mapper.writeValueAsString(rlist));
////	    } catch (IOException e) {
////	        e.printStackTrace();
////	    }
////	}
//	
////	----------------- GSON -------------------
////	@RequestMapping(value = "/reply_list")
////	public void resplyList(HttpServletRequest request,
////			HttpServletResponse response) {
////		BoardReplyDAO rdao = new BoardReplyDAO();
////		String bseqStr = request.getParameter("bseq");
////		int bseq = 0;
////		if(bseqStr != null || !bseqStr.equals("")) {
////			bseq = Integer.parseInt(bseqStr);
////		}
////		ArrayList<BoardReplyVO> rlist = rdao.select(bseq);
////		System.out.println(rlist.size());
////		//json		
////		Gson gson = new Gson(); // Or use new GsonBuilder().create();
////		String gsonStr = gson.toJson(rlist); // serializes target to Json
////		System.out.println(gsonStr); 
////		try {
////			PrintWriter out = response.getWriter();
////			out.println(gsonStr);
////		} catch (IOException e) {
////			e.printStackTrace();
////		}
////	}
//	
//
////	----------------- @ResponseBody -------------------	
//	@RequestMapping(value = "/reply_list")
//	@ResponseBody
//	public ArrayList<BoardReplyVO> resplyList(
//			@RequestParam("bseq") int bseq) {  
//		System.out.println("BSEQ:"+bseq);
//		ArrayList<BoardReplyVO> rlist = svc.svcReplyList(bseq);
//		System.out.println(rlist.size());
//		return rlist;
//	}
//	
//	
//	
////	----- @ResponseBody + @RequestBody ------	
////	@RequestMapping(value = "/reply_list")
////	@ResponseBody
////	public ArrayList<BoardReplyVO> resplyList(
////		@RequestBody BoardReplyVO rvo) { 
////		System.out.println("bseq:" + rvo.getBseq());
////		ArrayList<BoardReplyVO> rlist = svc.svcReplyList(rvo.getBseq());
////		System.out.println(rlist.size());
////		return rlist;
////	}
//	
//	
//	
//	@RequestMapping(value = "/reply_insert")
//	public String replyInsert(HttpServletRequest request) {
//		
//		String bseqStr = request.getParameter("bseq");
//		int bseq = 0;
//		if(bseqStr != null || !bseqStr.equals("")) {
//			bseq = Integer.parseInt(bseqStr);
//		}
//		String reply = request.getParameter("reply");
//		String userid = "kim"; //request.getParameter("userid");
//		
//		BoardReplyVO rvo = new BoardReplyVO();
//		rvo.setBseq(bseq);
//		rvo.setReply(reply);
//		rvo.setUserid(userid);
//		
//		int res = svc.svcReplyInsert(rvo);
//		return "redirect:/reply_list?bseq="+bseq;
//	}
//	
//	
//
//	
//	@RequestMapping(value = "/reply_delete")
//	public String resplyDelete(HttpServletRequest request) {
//		
//		//댓글지우기
//		String rseqStr = request.getParameter("rseq");
//		int res = 0;
//		if(rseqStr != null || !rseqStr.equals("")) {
//			int rseq = Integer.parseInt(rseqStr);
//			res = svc.svcReplyDelete(rseq); 
//		}
//		
//		//상세페이지로 돌아가기
//		int bseq = 0;
//		String bseqStr = request.getParameter("bseq");
//		if(bseqStr != null || !bseqStr.equals("")) {
//			bseq = Integer.parseInt(bseqStr);
//		}
//		return "redirect:/reply_list?bseq="+bseq;
//	}
//	
//	
//}
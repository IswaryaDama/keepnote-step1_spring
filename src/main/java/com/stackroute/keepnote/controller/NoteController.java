package com.stackroute.keepnote.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stackroute.keepnote.model.Note;
import com.stackroute.keepnote.repository.NoteRepository;

/*Annotate the class with @Controller annotation. @Controller annotation is used to mark 
 * any POJO class as a controller so that Spring can recognize this class as a Controller
 * */
@Controller
public class NoteController {
	
	private Note note;
	private NoteRepository noteRepo;
	private ApplicationContext context;
	public NoteController() {
		
		context = new ClassPathXmlApplicationContext("beans.xml");
		noteRepo= (NoteRepository)context.getBean("noteRepository");
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String hello(Model model) {
		List<Note> noteList=noteRepo.getAllNotes();
	    model.addAttribute("notelist",noteList);
		return "index";
	}
	
	@RequestMapping(value="saveNote", method=RequestMethod.POST)
	public  String addNote(@ModelAttribute("noteObject") Note noteObject,Model model) {
		note = (Note)context.getBean("note");
		List<Note> noteList = noteRepo.getAllNotes();
		model.addAttribute("notelist",noteList);
		note.setNoteId(noteObject.getNoteId());
		note.setNoteContent(noteObject.getNoteContent());
		note.setNoteTitle(noteObject.getNoteTitle());
		note.setNoteStatus(noteObject.getNoteStatus());
		note.setCreatedAt(LocalDate.now());				
		noteRepo.addNote(note);		
		noteList.stream().forEach(System.out::println);		
		model.addAttribute("noteObj",note);
		return "index";
	}
	
	@RequestMapping(value="/deleteNote",method=RequestMethod.GET)
	public String deleteNote(@RequestParam int noteId,Model model) {
		boolean isExist=noteRepo.exists(noteId);
		if(isExist) {
		noteRepo.deleteNote(noteId)	;
	}
		List<Note> noteList=noteRepo.getAllNotes();
		model.addAttribute("notelist",noteList);
		return "redirect:/";
	}
	
	/*
	 * From the problem statement, we can understand that the application
	 * requires us to implement the following functionalities.
	 * 
	 * 1. display the list of existing notes from the collection. Each note 
	 *    should contain Note Id, title, content, status and created date.
	 * 2. Add a new note which should contain the note id, title, content and status.
	 * 3. Delete an existing note.
	 * 4. Update an existing note.
	 */
	
	/* 
	 * Get the application context from resources/beans.xml file using ClassPathXmlApplicationContext() class.
	 * Retrieve the Note object from the context.
	 * Retrieve the NoteRepository object from the context.
	 */
	
	
	/*Define a handler method to read the existing notes by calling the getAllNotes() method 
	 * of the NoteRepository class and add it to the ModelMap which is an implementation of Map 
	 * for use when building model data for use with views. it should map to the default URL i.e. "/" */
	
	
	/*Define a handler method which will read the Note data from request parameters and
	 * save the note by calling the addNote() method of NoteRepository class. Please note 
	 * that the createdAt field should always be auto populated with system time and should not be accepted 
	 * from the user. Also, after saving the note, it should show the same along with existing 
	 * notes. Hence, reading notes has to be done here again and the retrieved notes object 
	 * should be sent back to the view using ModelMap.
	 * This handler method should map to the URL "/saveNote". 
	*/
	
	
	/* Define a handler method to delete an existing note by calling the deleteNote() method 
	 * of the NoteRepository class
	 * This handler method should map to the URL "/deleteNote" 
	*/
	
}
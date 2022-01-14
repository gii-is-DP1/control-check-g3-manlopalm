package org.springframework.samples.petclinic.recoveryroom;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recoveryroom")
public class RecoveryRoomController {
	
	private static final String VIEWS_RECOVERY_ROOM_CREATE_OR_UPDATE_FORM = "recoveryroom/createOrUpdateRecoveryRoomForm";
	
	private RecoveryRoomService recoveryRoomService;
	
	@Autowired
	public RecoveryRoomController(RecoveryRoomService recoveryRoomService) {
		this.recoveryRoomService = recoveryRoomService;
	}
	
	@GetMapping("/create")
	public String initCreationForm(ModelMap modelMap) {
		modelMap.addAttribute("recoveryRoom", new RecoveryRoom());
		modelMap.addAttribute("types", recoveryRoomService.getAllRecoveryRoomTypes());
		return VIEWS_RECOVERY_ROOM_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping("/create")
	public String processCreationForm(@Valid RecoveryRoom recoveryRoom, BindingResult result, ModelMap modelMap) {
		if(result.hasErrors()) {
			modelMap.addAttribute("recoveryRoom", recoveryRoom);
			modelMap.addAttribute("types", recoveryRoomService.getAllRecoveryRoomTypes());
			return VIEWS_RECOVERY_ROOM_CREATE_OR_UPDATE_FORM;
		} else {
			this.recoveryRoomService.save(recoveryRoom);
			modelMap.addAttribute("message", "Recovery room succesfully saved!");
		}
		return "welcome";
	}
    
}

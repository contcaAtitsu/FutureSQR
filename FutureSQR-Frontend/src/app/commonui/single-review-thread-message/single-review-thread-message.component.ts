import { Component, OnInit, Input,  SimpleChanges, ChangeDetectorRef  } from '@angular/core';
import { FormControl } from '@angular/forms';


// Services
import { ProjectDataQueryBackendService } from '../../backend/services/project-data-query-backend.service';

// Backend-Model
import { BackendModelThreadsMessage } from '../../backend/model/backend-model-threads-message'; 

@Component({
  selector: 'app-single-review-thread-message',
  templateUrl: './single-review-thread-message.component.html',
  styleUrls: ['./single-review-thread-message.component.css']
})
export class SingleReviewThreadMessageComponent implements OnInit {
	
	public formMessageText = new FormControl(); 
	public formAnswerText = new FormControl();
	
	@Input() activeProjectID: string = "";
	@Input() activeReviewID: string = "";
	@Input() activeMessage: BackendModelThreadsMessage = new BackendModelThreadsMessage();
	
	public isInAnswerMode:boolean = false;
	public isMessageEditMode:boolean = false;


	constructor(private projectDataQueryBackend : ProjectDataQueryBackendService, private cdr: ChangeDetectorRef) { }

	ngOnInit(): void {
	}
	
	ngOnChanges(changes: SimpleChanges): void {
		let _activeMessage:BackendModelThreadsMessage = changes.activeMessage?.currentValue;
		
		if(_activeMessage != undefined) {
			this.formMessageText.setValue(_activeMessage.message);
		}
		
		this.cdr.detectChanges();
	}	

	toggleEditTextMode():void {
		this.isMessageEditMode = !this.isMessageEditMode;
		this.cdr.detectChanges();
	}
	
	saveUpdatedMessage(): void {
		// TODO: save the current message into the backend.
		// XXXX: This is a dirty hack, we patch the value instead of reloading the page
		// TODO: render message to html.
		this.activeMessage.message = this.formMessageText.value;
		this.isMessageEditMode = false; 
	}
	
	resetMessage(): void {
		this.formMessageText.setValue( this.activeMessage.message );
	}
	
	cancelMessageEdit(): void  {
		this.formMessageText.setValue( this.activeMessage.message );
		this.isMessageEditMode = false;
	}
	
	activateReplyMode(): void {
		this.isInAnswerMode = true;
		let elementid = "reply"+this.activeMessage.messageId
		console.log(elementid)
		let element = document.getElementById(elementid);
		console.log(element)
		// (<any>this.formAnswerText.control).nativeElement.focus();
	}
	
	sendReplyMessage(): void {
		let that = this;
		let message = that.formAnswerText.value;
		
		this.projectDataQueryBackend.replyThreadMessageForReview(
			this.activeProjectID, 
			this.activeReviewID,
			this.activeMessage.threadId, 
			"mindscan-de", 
			this.activeMessage.messageId, 
			message
		).subscribe(
			data => {
				that.isInAnswerMode = false;
				that.formAnswerText.setValue("");
				
				// TODO actually we must reload 
			},
			error => {}
		);
		
	}
}

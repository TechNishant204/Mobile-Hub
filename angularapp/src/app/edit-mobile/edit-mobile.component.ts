import { Component, OnInit } from '@angular/core';
import { MobileService } from '../services/mobile.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Mobile } from '../models/mobile.model';

@Component({
  selector: 'app-edit-mobile',
  templateUrl: './edit-mobile.component.html',
  styleUrls: ['./edit-mobile.component.css']
})
export class EditMobileComponent implements OnInit {

  editMobileForm: FormGroup;

  editedMobileObject:Mobile;

  
  constructor(
    private fb: FormBuilder,
    private mobileService: MobileService,
    private router: Router
  ) {
    this.editMobileForm = this.fb.group({
      model: [this.mobileToEdit.model,Validators.required],
      brand: [this.mobileToEdit.brand,Validators.required],
      imageUrl: [this.mobileToEdit.imageUrl,Validators.required],
      description: [this.mobileToEdit.description,Validators.required],
      quantity: [this.mobileToEdit.quantity,[Validators.required,Validators.min(1)]],
      price: [this.mobileToEdit.price,[Validators.required,Validators.min(1000),Validators.max(200000)]]
    });
  }
  ngOnInit(): void {  
  }
  mobileToEdit:Mobile=this.mobileService.editMobileObj;
  
    updateMobile() {
      if (this.editMobileForm.valid) {
        this.mobileService.updateMobile(this.mobileToEdit.mobileId,this.editMobileForm.value).subscribe(() => {
          this.router.navigate(['/viewmobiles']);
        });
      }
    }

  
    cancel(): void {
      this.router.navigate(['/viewmobiles']);
    }
  }

  

  
  

   

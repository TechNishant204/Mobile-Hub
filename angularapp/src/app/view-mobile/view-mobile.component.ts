import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { MobileService } from '../services/mobile.service';
import { Mobile } from '../models/mobile.model';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-mobile',
  templateUrl: './view-mobile.component.html',
  styleUrls: ['./view-mobile.component.css']
})
export class ViewMobileComponent implements OnInit {
   
  updateModal : boolean = false;
  delModal: boolean = false;

  mobiles: any[] = [];
  // mobiles: Mobile[] = [
  //   { mobileId: 1, model: 'iPhone Pro', description: 'Depicts speed and power', price: 999, imageUrl: 'path_to_image' },
  //   { mobileId: 2, model: 'MI NOTE 4', description: 'Depicts style', price: 149, imageUrl: 'path_to_image' },
  //   { mobileId: 3, model: 'POCO X4', description: 'Iconic Design', price: 299, imageUrl: 'path_to_image' },
  //   { mobileId: 4, model: 'A3 galaxy', description: 'Stunning technology', price: 399, imageUrl: 'path_to_image' }
  // ];

  constructor(private mobileService: MobileService,private route:Router) {}

  searchMobiles(){

    if(this.searchTerm===""){

      this.mobileService.viewAllMobile().subscribe((data) => {
        this.mobiles = data;
      });

    }else{

      this.mobiles=this.mobiles.filter(data=>JSON.stringify(data).toLowerCase().includes(this.searchTerm.toLowerCase()));

    }

  }
 
searchTerm:string
 
  
  ngOnInit(): void {
    this.mobileService.viewAllMobile().subscribe(data => {
      this.mobiles = data;
    });
  }

  editMobile(editmobile:Mobile){
        this.mobileService.editMobileObj=editmobile;
        console.log(editmobile);
        this.route.navigate(['/editmobile']);

  }

  deleteMobile(mobileId:number){

         this.mobileService.deleteMobile(mobileId).subscribe(data=>{
          Swal.fire({text:"mobile deleted successfully",icon:"success"});
               this.ngOnInit();      
         },
         (error)=>{
           Swal.fire({text:"mobile cannot be deleted ",icon:"error"});
         }

         );
       
  }
  editModal(){
    this.updateModal = true;
  }
  editModalCancel(){
    this.updateModal = false;
  }
 
  deleteModal(){
    this.delModal = true;
  }
  deleteModalCancel(){
    this.delModal = false;
  }


}

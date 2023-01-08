import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AuthenticationService } from '../services/authentication.service';
import { UserServiceService } from '../services/user-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {
  error : string = '';
  registerForm = this.fb.group({
    'name' : ['', Validators.required],
    'email' : ['', Validators.required],
    'contactNo' : ['',Validators.required],
    'password' : ['', Validators.required],
    // 'confirmPassword' : ['',Validators.required],
    'address' : this.fb.group({
      'houseNo': [''],
      'streetName' : [''],
      'city':[''],
      'pinCode' : ['']
    })
  })

  get name() {return this.registerForm.get('name')}
  get email() {return this.registerForm.get('email')}
  get contactNo() {return this.registerForm.get('contactNo')}
  get password() {return this.registerForm.get('password')}
  // get confirmPassword() {return this.registerForm.get('confirmPassword')}
  get address() {return this.registerForm.get('address')}
  get houseNo() {return this.address?.get('houseNo')}
  get streetName() {return this.address?.get('streetName')}
  get city() {return this.address?.get('city')}
  get pinCode() {return this.address?.get('pinCode')}

  constructor(private fb:FormBuilder, private userServiceService:UserServiceService, private router:Router) {}

  onSubmit() {
    console.log(this.registerForm.value);
    this.userServiceService.registerUser(this.registerForm.value).subscribe({
      next : data => {
        console.log(data);
        this.router.navigateByUrl('/login');
      },
      error : err =>{
        if(err.status ==409 ){
          this.error = "User Already Exists Please Login"
        }else {
          this.error = "Could not Register! Please Try After Some Time"

        }
        console.log(err)
      }
      
    })
  }
}

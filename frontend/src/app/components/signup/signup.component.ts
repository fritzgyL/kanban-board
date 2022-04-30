import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user/user';
import { AuthStore } from 'src/app/stores/auth.store';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  firstName: string = ''
  lastName: string = ''
  email: string = ''
  password: string = ''
  isSignUpFailed: boolean = false;
  isSignUpSuccessful: boolean = false;

  constructor(private authStore: AuthStore, private router: Router) { }

  ngOnInit(): void {
  }

  onSignUp(f: NgForm) {
    if (f.valid) {
      const user = new User();
      user.firstName = this.firstName;
      user.lastName = this.lastName;
      user.email = this.email;
      user.password = this.password;
      this.authStore.signup(user).subscribe((user) => {
        if (user !== null) {
          this.isSignUpSuccessful = true;
        }
        else {
          this.isSignUpFailed = true;
        }
      });
    }
  }

  onClickToLogin() {
    this.router.navigate(['/login']);
  }

}

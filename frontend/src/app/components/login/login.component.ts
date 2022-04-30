import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user/user';
import { AuthStore } from 'src/app/stores/auth.store';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email: string = ''
  password: string = ''
  isAuthenticationFailed: boolean = false;

  constructor(private authStore: AuthStore, private router: Router) { }

  ngOnInit(): void {
  }

  onLogin() {
    if (this.email !== '' && this.password !== '') {
      const user = new User();
      user.email = this.email;
      user.password = this.password;
      this.authStore.login(user).subscribe((user) => {
        if (user !== null) {
          this.router.navigate(['/dashboard']);
        }
        else {
          this.isAuthenticationFailed = true;
        }
      });
    }
  }

  onClickToSignUp() {
    this.router.navigate(['/signup']);
  }

}

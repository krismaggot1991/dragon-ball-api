import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { LoginModel } from 'src/app/heroes/components/user.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styles: [
  ]
})
export class LoginPageComponent {

  loginModel: LoginModel = new LoginModel();
  myForm: FormGroup;
  constructor(private authService: AuthService, private router: Router, private fb: FormBuilder) {

    this.myForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(3)]],
    });

  }

  onLogin(): void {
    if (this.myForm.invalid) return;
    const { email, password } = this.myForm.value;

    this.loginModel.email = email;
    this.loginModel.password = password;
    console.log(this.loginModel);

    this.authService.login(this.loginModel).subscribe(resp => {
      console.log(resp);
      if (resp.code === '200') {
        this.router.navigate(['/']);
      } else {
        Swal.fire({
          allowOutsideClick: true,
          title: 'Login incorrecto!',
          text: "Por favor verifique su usuario y correo."
        });
      }
    }, (err) => {
      console.log(err.error.error.message);
      Swal.fire({
        allowOutsideClick: true,
        title: 'Error en el registro...',
        text: err.error.error.message
      });

    });

  }

}

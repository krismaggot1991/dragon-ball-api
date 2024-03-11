import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserModel } from 'src/app/heroes/interfaces/user.model';
import Swal from 'sweetalert2';
import { HeroesService } from 'src/app/heroes/services/heroes.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styles: [
  ]
})
export class RegisterPageComponent implements OnInit {
  usuario: UserModel = new UserModel();
  myForm: FormGroup;
  constructor(private auth: HeroesService, private router: Router, private fb: FormBuilder) {

    this.myForm = this.fb.group({
      userName: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(3)]],
    });

  }



  ngOnInit() {

  }


  login() {

    if (this.myForm.invalid) return;
    const { email, password, userName } = this.myForm.value;

    this.usuario.userName = userName;
    this.usuario.email = email;
    this.usuario.password = password;
    console.log(this.usuario);

    this.auth.registerUser(this.usuario).subscribe(resp => {
      console.log(resp);

      if (resp.code === '200') {
        Swal.fire({
          allowOutsideClick: true,
          title: 'Exito!',
          text: "Usuario registrado exitosamente"
        });
        this.myForm.reset();
      } else {
        Swal.fire({
          allowOutsideClick: true,
          title: 'Usuario ya registrado.',
          text: "Por favor ingrese un usuario y correo nuevo"
        });
      }
    }, (err) => {
      console.log(err.error.error.message);

      // mostrar alerta
      Swal.fire({
        // para que el usuario no pueda cerrar la ventana dando clic afuera
        allowOutsideClick: true,
        title: 'Error en el registro...',
        text: err.error.error.message
      });

    });
  }

}

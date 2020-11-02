import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { first } from 'rxjs/operators';
import { AuthService } from '../auth.service';
import { UsuarioService } from '../services/usuario.service';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent implements OnInit {

  registerForm: FormGroup;
  loading = false;
  submitted = false;

  constructor(
      private formBuilder: FormBuilder,
      private router: Router,
      private authService: AuthService,
      private userService: UsuarioService
  ) {
    
  }

  ngOnInit() {
      this.registerForm = this.formBuilder.group({
          dni: ['', Validators.required],
          password: ['', [Validators.required]],
          nombre: ['', [Validators.required]],
          apellidos: ['', Validators.required],
          correo: ['', Validators.required],
          telefono: ['', Validators.required],
          tipousuario: ['', Validators.required]
          
      });
  }

  // getter para acceder a los controles del form
  get f() { return this.registerForm.controls; }

  onSubmit() {
      this.submitted = true;


      // para aquí si el form es inválido
      if (this.registerForm.invalid) {
          return;
      }

      

      this.loading = true;
      alert(this.registerForm.value)
      this.userService.register(this.registerForm.value)
          .pipe(first())
          .subscribe(
              data => {
                  this.router.navigate(['/login'], { queryParams: { registered: true } });
              },
              error => {
                  this.loading = false;
              });

      function allLetter(inputtxt) {
          var letters = /^[A-Za-z]+$/;
          var space = ' ';
          if (inputtxt.value.match(letters) || inputtxt.value.match(space) || inputtxt.value.match("ñ") || inputtxt.value.match("Ñ")) {
              return true;
          }
          else {
              return false;
          }
      }

      function checkPass(inputText) {
          var all = /^[A-Za-z0-9,!,@,#,$,%,^,&,*,?,_,~]+$/;

          if (inputText.value.match(all)) {
              return true;
          } else {
              return false;
          }

      }

  }


}


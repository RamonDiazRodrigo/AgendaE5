import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegistroComponent } from './registro/registro.component';
import { ReunionesComponent } from './reuniones/reuniones.component';
import { UsuariosAdminComponent } from './usuarios-admin/usuarios-admin.component';


const routes: Routes = [
  {
    path:'',
    redirectTo: '/login',
    pathMatch: 'full'
  },
  {
    path: 'reuniones', component: ReunionesComponent
  },
  {
    path: 'login', component: LoginComponent
  },
  {
    path: 'register', component: RegistroComponent
  },
  {
    path: 'usuarios', component: UsuariosAdminComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

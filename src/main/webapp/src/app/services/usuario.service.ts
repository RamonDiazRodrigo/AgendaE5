import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  currentUserSubject: any;
  constructor(private http: HttpClient) { }

  register(user) {
    return this.http.post(`${environment.apiUrl}`, user);
}

delete(id) {
    return this.http.delete(`${environment.apiUrl}/usuarios/${id}`);
}
update(user,id) {
    return this.http.put(`${environment.apiUrl}/usuarios/${id}`, user);
}
}

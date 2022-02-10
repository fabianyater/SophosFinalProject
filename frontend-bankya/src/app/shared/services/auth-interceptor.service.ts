import { Router } from '@angular/router';
import { AuthService } from './auth.service';
import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthInterceptorService implements HttpInterceptor {
  constructor(private authService: AuthService, private router: Router) {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    let request = req;
    const token = this.authService.getToken();
    if (token != null) {
      request = req.clone({
        setHeaders: {
          Authorization: token,
        },
      });
    }
    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
          if (error.status === 403) {
              this.router.navigateByUrl('/login');
          }
          return throwError(error);
      })
  );
  }
}

import { NgModule } from '@angular/core';
import { RouterModule, Routes, CanActivate } from '@angular/router';
import { CartViewComponent } from './cart-view/cart-view.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ProductEditFormComponent } from './product-edit-form/product-edit-form.component';
import { ProductFormComponent } from './product-form/product-form.component';
import { ProductViewComponent } from './product-view/product-view.component';
import { RegistrationComponent } from './registration/registration.component';
import { AdminGuard } from './services/auth/admin.guard';
import { AuthGuard } from './services/auth/auth.guard';


const routes: Routes = [
  {
  path : '',
  component : ProductViewComponent
  },
  {
    path : 'home',
    component : ProductViewComponent
    },
  {
    path : 'login',
    component : LoginComponent
  },
  {
    path : 'register',
    component : RegistrationComponent
  },
  {
    path : 'cart',
    component : CartViewComponent,
    canActivate : [AuthGuard]
  },
  {
    path : 'add-product',
    component : ProductFormComponent,
    canActivate : [AdminGuard]
  },
  {
    path : 'edit-product',
    component : ProductEditFormComponent,
    canActivate : [AdminGuard]
  },

  {
    path : '**',
    component : ProductViewComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

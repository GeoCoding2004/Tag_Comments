import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'login',
    loadComponent: () => import('./feature_components/login/login').then(m => m.LoginPage)
  },
  {
    path: 'register',
    loadComponent: () => import('./feature_components/register/register').then(m => m.RegisterPage)
  },

  {
    path: 'dashboard',
    loadComponent: () => import('./feature_components/dashboard/dashboard').then(m => m.Dashboard)
  },


  {
    path: 'create-comment',
    loadComponent: () => import('./feature_components/create-comment/create-comment').then(m => m.CreateCommentComponent)
  },

  {
    path: 'manage-comments',
    loadComponent: () => import('./feature_components/manage-comments/manage-comments').then(m => m.ManageComments)
  },


  {
    path: 'create-tag',
    loadComponent: () => import('./feature_components/create-tag/create-tag').then(m => m.CreateTag)
  },

  {
    path: 'manage-tags',
    loadComponent: () => import('./feature_components/manage-tags/manage-tags').then(m => m.ManageTags)
  },

  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'  
  }
];

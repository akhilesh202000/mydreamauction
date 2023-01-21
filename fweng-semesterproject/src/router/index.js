import { createRouter, createWebHistory } from 'vue-router';

import HomeView from '@/views/HomeView';
import RegisterView from '@/views/RegisterView';
import LoginView from '@/views/LoginView';
import HelpView from '@/views/HelpView';
import ImprintView from '@/views/ImprintView';
import ProfileVue from '@/views/ProfileVue';
import AdminVue from '@/views/AdminVue';
import EditUserVue from '@/views/EditUserVue';

const routes = [
   {
      path: '/home',
      name: 'home',
      component: HomeView,
   },
   {
      path: '/register',
      name: 'register',
      component: RegisterView,
   },
   {
      path: '/',
      name: 'login',
      component: LoginView,
   },
   {
      path: '/help',
      name: 'Help',
      component: HelpView,
   },
   {
      path: '/imprint',
      name: 'imprint',
      component: ImprintView,
   },
   {
      path: '/profile',
      name: 'Profile',
      component: ProfileVue,
   },
   {
      path: '/admin',
      name: 'Admin',
      component: AdminVue,
   },
   {
      path: '/edit',
      name: 'Edit',
      component: EditUserVue,
   },
]

const router = createRouter({
   routes,
   history: createWebHistory(process.env.BASE_URL),
});

export default router;
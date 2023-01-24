import { createRouter, createWebHistory } from 'vue-router';

import HomeView from '@/views/HomeView';
import RegisterView from '@/views/RegisterView';
import LoginView from '@/views/LoginView';
import HelpView from '@/views/HelpView';
import ImprintView from '@/views/ImprintView';
import ProfileVue from '@/views/ProfileVue';
import AdminVue from '@/views/AdminVue';
import EditUserVue from '@/views/EditUserVue';
import EditProductVue from '@/views/EditProductVue';
import ProductVue from '@/views/ProductVue';
import AuctionDetailVue from '@/views/AuctionDetailVue';
import CreateAuctionVue from '@/views/CreateAuctionVue';
import MyBidsVue from '@/views/MyBidsVue';
import MyOffersVue from '@/views/MyOffersVue';


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
   {
      path: '/editproduct',
      name: 'Edit Product',
      component: EditProductVue,
   },
   {
      path: '/product',
      name: 'Product',
      component: ProductVue,
   },
   {
      path: '/auctiondetail',
      name: 'Auction detail',
      component: AuctionDetailVue,
   },
   {
      path: '/createauction',
      name: 'Create auction',
      component: CreateAuctionVue,
   },
   {
      path: '/mybids',
      name: 'My bids',
      component: MyBidsVue,
   },
   {
      path: '/myoffers',
      name: 'My myoffers',
      component: MyOffersVue,
   },
]

const router = createRouter({
   routes,
   history: createWebHistory(process.env.BASE_URL),
});

export default router;
function logout() {
       localStorage.removeItem('access_token');

       location.href = '/logout';
}
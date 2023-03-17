import React from 'react'

function Header() {
  return (
    <div>
      <nav class="navbar navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">My Review App</a>
            <form class="d-flex">
            <a class="navbar-brand" href="/">About</a>
            <a class="navbar-brand" href="/">Contact</a>
      <button class="btn btn-outline-light" type="submit">Sign in</button>
    </form>
        </div>
        </nav>
    </div>
  )
}

export default Header

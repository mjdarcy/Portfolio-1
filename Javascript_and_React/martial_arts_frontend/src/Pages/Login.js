import React from 'react';
import NavBar from '../Components/NavBar';
import LoginForm from '../Components/LoginForm';

export default class Login extends React.Component
{
  constructor(props)
  {
    super(props);
    this.state = 
    {
        login: true,
        data:{ }
    }
    this.getUser = this.getUser.bind(this);
}

    getUser(username, password)
    {
        fetch(this.props.apiUrl + "/user/" + username + "/" + password)
            .then(response => response.json())
            .then(data => this.setState({data: data}));
        console.log(this.state.data);
    }

    render()
    {
        return(
            <div>
                <NavBar/>
                <LoginForm getUser={this.getUser}/>
                <a href={"http://localhost:3000/register"}>Register</a>
            </div>)
    }
}
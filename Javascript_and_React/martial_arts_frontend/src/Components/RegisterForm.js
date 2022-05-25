import React from 'react';

export default class RegisterForm extends React.Component
{
  constructor(props)
  {
    super(props);
    this.state = 
    {
        user:
        {
        username: "",
        password: "",
        password2: "",
        firstName: "",
        lastName: "",
        email: ""
        },
        style:
        {
            borderStyle: "solid"
        }
    }
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
}

    handleChange(event)
    {
        let fieldName = event.target.name;
        let value = event.target.value;
        let userUpdate = this.state.user
        userUpdate[fieldName] = value;
        this.setState({user: userUpdate});
    }

    handleSubmit(event)
    {
        event.preventDefault();
        let u = this.state.user;
        if(u.password == u.password2) this.props.postUser(u.username, u.password, u.firstName, u.lastName, u.email);
        else alert("Passwords do not match.");
    }
    
    render()
    {
        return(
        <div style={this.state.style}>
            <form onSubmit={this.handleSubmit}>
                <label>Username: <input name="username" type="text" onChange={this.handleChange}/></label><br/>
                <label>Password: <input name="password" type="text" onChange={this.handleChange}/></label><br/>
                <label>Password again: <input name="password2" type="text" onChange={this.handleChange}/></label><br/>
                <label>First name: <input name="firstName" type="text" onChange={this.handleChange}/></label><br/>
                <label>Last name: <input name="lastName" type="text" onChange={this.handleChange}/></label><br/>
                <label>Email: <input name="email" type="text" onChange={this.handleChange}/></label><br/>
                <button>Login</button>
            </form>
        </div>)
    }
}
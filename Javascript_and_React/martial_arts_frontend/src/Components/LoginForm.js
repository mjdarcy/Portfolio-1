import React from 'react';

export default class LoginForm extends React.Component
{
  constructor(props)
  {
    super(props);
    this.state = 
    {
        user:{username: "", password: ""},
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
        console.log(u.username + " " + u.password);
        this.props.getUser(u.username, u.password);
    }

    render()
    {
        return(
        <div style={this.state.style}>
            <form onSubmit={this.handleSubmit}>
                <label>Username: <input name="username" type="text" onChange={this.handleChange}/></label><br/>
                <label>Password: <input name="password" type="text" onChange={this.handleChange}/></label><br/>
                <button>Login</button>
            </form>
        </div>)
    }
}
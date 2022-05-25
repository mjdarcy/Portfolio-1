import React from 'react';

export default class FeedbackForm extends React.Component
{
  constructor(props)
  {
    super(props);
    this.state = 
    {
        content: "",
        style:
        {
            borderStyle: "solid"
        }
    }
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
}

    handleSubmit(event)
    {
        event.preventDefault();
        this.props.postMessage("aj", this.state.content, "feedback");
    }

    handleChange(event)
    {
      let fieldName = event.target.name;
      let value = event.target.value;
      this.setState({ [fieldName]: value })
    }

    render()
    {
        return(
        <div style={this.state.style}>
              <form onSubmit={this.handleSubmit}>
                  <label> Message: <br/> <textarea name="content" type="textarea" rows="30" cols="60" style={{resize: "none"}} value={this.state.content} onChange={this.handleChange}/></label><br/>
                  <input type="submit" value="Submit" />
              </form>
        </div>)
    }
}
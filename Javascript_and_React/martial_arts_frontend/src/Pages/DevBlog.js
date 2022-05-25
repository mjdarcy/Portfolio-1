import React from 'react';
import NavBar from '../Components/NavBar';
import BlogPost from '../Components/BlogPost';

export default class DevBlog extends React.Component
{
  constructor(props)
  {
    super(props);
    this.state = 
    {
        style:
        {
            borderStyle: "solid"
        },
        data:[]
    }
}

    componentDidMount()
    {
        this.getMessages();
    }

    getMessages()
    {
    fetch(this.props.apiUrl + "/message/received/devblog")
        .then(response => response.json())
        .then(data => this.setState({data: data}));
    }

    blogPostList()
    {
        return this.state.data.map(e => <BlogPost key={e.id} date={e.datePosted} content={e.contents}/>)
    }

    render()
    {
        let list = this.blogPostList().reverse();
        return(
            <div style={this.state.style}>
                <NavBar/>
                {list}
            </div>
        )
    }
}
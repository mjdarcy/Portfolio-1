import React from 'react';
import NavBar from '../Components/NavBar';
import TechList from '../Components/TechList';
import TechVideo from '../Components/TechVideo';
import TechDescription from '../Components/TechDescription';
import TechForm from '../Components/TechForm'

export default class Library extends React.Component
{
  constructor(props)
  {
    super(props);
    this.state = 
    {
        selectedTechnique:
        {
            id: 0,
            name: "",
            type: "",
            description: "",
            video: "",
            rating: 1
        },
        style:
        {
            borderStyle: "solid"
        },
        data:{ }
    }
    this.setSelectedTechnique = this.setSelectedTechnique.bind(this);
    this.getAllUserFavorites = this.getAllUserFavorites.bind(this);
}

    setSelectedTechnique(tech)
    {
        this.setState({ selectedTechnique: tech })
    }

    postUserTechnique(form)
    {
        fetch(this.props.apiUrl + "/technique",
        {method: "POST", body: form})
            .catch((e) => console.log(e));

        alert("Technique posted.");
    }

    /*
    postUserFavorite(user, id)
    {
        //should be better to do this in spring with cascades.
        fetch(this.props.apiUrl + "/favorite",
        {method: "POST",
        body: JSON.stringify(
        {username: user,
        techId: id})})
            .catch((e) => console.log(e));
    }*/

    getAllUserFavorites(username)
    {
        console.log(this.props.apiUrl +"/technique/fav/"+username)
        fetch(this.props.apiUrl + "/technique/fav/" + username)
            .then(response => response.json())
            .then(data => this.setState({data: data}));
    }

    deleteUserTechnique(username, techId)
    {
        fetch(this.props.apiUrl + "/technique",
        {method: "DELETE", body: JSON.stringify({techId: techId})})
            .catch((e) => console.log(e));
        this.deleteUserFavorite(username, techId);
    }

    render()
    {
        let page = "Library";
        return(
        <div style={this.state.style}>
            <NavBar page={page}/>
            <TechList apiUrl={this.props.apiUrl} setSelectedTechnique={this.setSelectedTechnique} getTechniques={this.getAllUserFavorites} page={page}/>
            <TechVideo videoUrl={this.props.apiUrl + "/technique/video/" + this.state.selectedTechnique.id}/>
            <TechDescription desc={this.state.selectedTechnique.description}/>
            <TechForm apiUrl={this.props.apiUrl}/>
        </div>
        )
    }
}
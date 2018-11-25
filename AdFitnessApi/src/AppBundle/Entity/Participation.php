<?php

namespace AppBundle\Entity;

/**
 * Participation
 */
class Participation
{
    /**
     * @var int
     */
    private $id;

    /**
     * @var \stdClass
     */
    private $user;

    /**
     * @var \stdClass
     */
    private $session;


    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @return \stdClass
     */
    public function getUser()
    {
        return $this->user;
    }

    /**
     * @param \stdClass $user
     */
    public function setUser($user)
    {
        $this->user = $user;
    }



    /**
     * Set session
     *
     * @param \stdClass $session
     *
     * @return Participation
     */
    public function setSession($session)
    {
        $this->session = $session;

        return $this;
    }

    /**
     * Get session
     *
     * @return \stdClass
     */
    public function getSession()
    {
        return $this->session;
    }
}

